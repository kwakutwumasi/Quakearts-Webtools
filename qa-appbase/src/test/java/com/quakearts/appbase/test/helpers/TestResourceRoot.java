package com.quakearts.appbase.test.helpers;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.TrackedWebResource;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.FileResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.JarWarResourceSet;
import org.apache.catalina.webresources.WarResourceSet;
import org.apache.tomcat.util.http.RequestUtil;

public class TestResourceRoot implements WebResourceRoot {

    private final List<WebResourceSet> preResources = new ArrayList<>();
    private WebResourceSet main;
    private final List<WebResourceSet> classResources = new ArrayList<>();
    private final List<WebResourceSet> jarResources = new ArrayList<>();
    private final List<WebResourceSet> postResources = new ArrayList<>();

    private final List<WebResourceSet> mainResources = new ArrayList<>();
    private final List<List<WebResourceSet>> allResources =
            new ArrayList<>();
    {
        allResources.add(preResources);
        allResources.add(mainResources);
        allResources.add(classResources);
        allResources.add(jarResources);
        allResources.add(postResources);
    }

	@Override
	public void addLifecycleListener(LifecycleListener listener) {
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		return null;
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
	}

	@Override
	public void init() throws LifecycleException {
	}

	@Override
	public void start() throws LifecycleException {
        mainResources.clear();

        main = createMainResourceSet();

        mainResources.add(main);

        for (List<WebResourceSet> list : allResources) {
            // Skip class resources since they are started below
            if (list != classResources) {
                for (WebResourceSet webResourceSet : list) {
                    webResourceSet.start();
                }
            }
        }

        // This has to be called after the other resources have been started
        // else it won't find all the matching resources
        processWebInfLib();
        // Need to start the newly found resources
        for (WebResourceSet classResource : classResources) {
            classResource.start();
        }
	}
	
	Context context;
	
    protected WebResourceSet createMainResourceSet() {
        String docBase = context.getDocBase();

        WebResourceSet mainResourceSet;
        if (docBase == null) {
            mainResourceSet = new EmptyResourceSet(this);
        } else {
            File f = new File(docBase);
            if (!f.isAbsolute()) {
                f = new File(((Host)context.getParent()).getAppBaseFile(), f.getPath());
            }
            if (f.isDirectory()) {
                mainResourceSet = new DirResourceSet(this, "/", f.getAbsolutePath(), "/");
            } else if(f.isFile() && docBase.endsWith(".war")) {
                mainResourceSet = new WarResourceSet(this, "/", f.getAbsolutePath());
            } else {
                throw new IllegalArgumentException("standardRoot.startInvalidMain");
            }
        }

        return mainResourceSet;
    }

    private void processWebInfLib() {
        WebResource[] possibleJars = listResources("/WEB-INF/lib", false);

        for (WebResource possibleJar : possibleJars) {
            if (possibleJar.isFile() && possibleJar.getName().endsWith(".jar")) {
                createWebResourceSet(ResourceSetType.CLASSES_JAR,
                        "/WEB-INF/classes", possibleJar.getURL(), "/");
            }
        }
    }
    
	@Override
	public void stop() throws LifecycleException {
	}

	@Override
	public void destroy() throws LifecycleException {
	}

	@Override
	public LifecycleState getState() {
		return LifecycleState.STARTED;
	}

	@Override
	public String getStateName() {
		return null;
	}

	@Override
	public WebResource getResource(String path) {
        return getResource(path, true, false);
	}

    private WebResource getResource(String path, boolean validate,
            boolean useClassLoaderResources) {
        if (validate) {
            path = validate(path);
        }

        return getResourceInternal(path, useClassLoaderResources);
    }

    protected final WebResource getResourceInternal(String path,
            boolean useClassLoaderResources) {
        WebResource result = null;
        WebResource virtual = null;
        WebResource mainEmpty = null;
        for (List<WebResourceSet> list : allResources) {
            for (WebResourceSet webResourceSet : list) {
                if (!useClassLoaderResources &&  !webResourceSet.getClassLoaderOnly() ||
                        useClassLoaderResources && !webResourceSet.getStaticOnly()) {
                    result = webResourceSet.getResource(path);
                    if (result.exists()) {
                        return result;
                    }
                    if (virtual == null) {
                        if (result.isVirtual()) {
                            virtual = result;
                        } else if (main.equals(webResourceSet)) {
                            mainEmpty = result;
                        }
                    }
                }
            }
        }

        // Use the first virtual result if no real result was found
        if (virtual != null) {
            return virtual;
        }

        // Default is empty resource in main resources
        return mainEmpty;
    }
    
    private String validate(String path) {

        if (path == null || path.length() == 0 || !path.startsWith("/")) {
            throw new IllegalArgumentException("standardRoot.invalidPath");
        }

        String result;
        if (File.separatorChar == '\\') {
            // On Windows '\\' is a separator so in case a Windows style
            // separator has managed to make it into the path, replace it.
            result = RequestUtil.normalize(path, true);
        } else {
            // On UNIX and similar systems, '\\' is a valid file name so do not
            // convert it to '/'
            result = RequestUtil.normalize(path, false);
        }
        if (result == null || result.length() == 0 || !result.startsWith("/")) {
            throw new IllegalArgumentException("standardRoot.invalidPathNormal");
        }

        return result;
    }
    
	@Override
	public WebResource[] getResources(String path) {
		
		return null;
	}

    @Override
    public WebResource getClassLoaderResource(String path) {
        return getResource("/WEB-INF/classes" + path, true, true);
    }

    @Override
    public WebResource[] getClassLoaderResources(String path) {
        return getResources("/WEB-INF/classes" + path, true);
    }

    private WebResource[] getResources(String path,
            boolean useClassLoaderResources) {
        path = validate(path);
        return getResourcesInternal(path, useClassLoaderResources);
    }

    protected WebResource[] getResourcesInternal(String path,
            boolean useClassLoaderResources) {
        List<WebResource> result = new ArrayList<>();
        for (List<WebResourceSet> list : allResources) {
            for (WebResourceSet webResourceSet : list) {
                if (useClassLoaderResources || !webResourceSet.getClassLoaderOnly()) {
                    WebResource webResource = webResourceSet.getResource(path);
                    if (webResource.exists()) {
                        result.add(webResource);
                    }
                }
            }
        }

        if (result.size() == 0) {
            result.add(main.getResource(path));
        }

        return result.toArray(new WebResource[result.size()]);
    }
    
	@Override
	public String[] list(String path) {
		
		return null;
	}

	@Override
	public Set<String> listWebAppPaths(String path) {
		
		return null;
	}

	@Override
	public WebResource[] listResources(String path) {
        return listResources(path, true);
	}
	
    private WebResource[] listResources(String path, boolean validate) {
        if (validate) {
            path = validate(path);
        }

        String[] resources = list(path, false);
        WebResource[] result = new WebResource[resources.length];
        for (int i = 0; i < resources.length; i++) {
            if (path.charAt(path.length() - 1) == '/') {
                result[i] = getResource(path + resources[i], false, false);
            } else {
                result[i] = getResource(path + '/' + resources[i], false, false);
            }
        }
        return result;
    }

    private String[] list(String path, boolean validate) {
        if (validate) {
            path = validate(path);
        }

        HashSet<String> result = new LinkedHashSet<>();
        for (List<WebResourceSet> list : allResources) {
            for (WebResourceSet webResourceSet : list) {
                if (!webResourceSet.getClassLoaderOnly()) {
                    String[] entries = webResourceSet.list(path);
                    for (String entry : entries) {
                        result.add(entry);
                    }
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
    
	@Override
	public boolean mkdir(String path) {
		
		return false;
	}

	@Override
	public boolean write(String path, InputStream is, boolean overwrite) {
		
		return false;
	}

    @Override
    public void createWebResourceSet(ResourceSetType type, String webAppMount,
            URL url, String internalPath) {
        BaseLocation baseLocation = new BaseLocation(url);
        createWebResourceSet(type, webAppMount, baseLocation.getBasePath(),
                baseLocation.getArchivePath(), internalPath);
    }

    // Unit tests need to access this class
    static class BaseLocation {

        private final String basePath;
        private final String archivePath;

        BaseLocation(URL url) {
            File f = null;

            if ("jar".equals(url.getProtocol()) || "war".equals(url.getProtocol())) {
                String jarUrl = url.toString();
                int endOfFileUrl = -1;
                if ("jar".equals(url.getProtocol())) {
                    endOfFileUrl = jarUrl.indexOf("!/");
                } else {
                    endOfFileUrl = jarUrl.indexOf("*/");
                }
                String fileUrl = jarUrl.substring(4, endOfFileUrl);
                try {
                    f = new File(new URL(fileUrl).toURI());
                } catch (MalformedURLException | URISyntaxException e) {
                    throw new IllegalArgumentException(e);
                }
                int startOfArchivePath = endOfFileUrl + 2;
                if (jarUrl.length() >  startOfArchivePath) {
                    archivePath = jarUrl.substring(startOfArchivePath);
                } else {
                    archivePath = null;
                }
            } else if ("file".equals(url.getProtocol())){
                try {
                    f = new File(url.toURI());
                } catch (URISyntaxException e) {
                    throw new IllegalArgumentException(e);
                }
                archivePath = null;
            } else {
                throw new IllegalArgumentException("standardRoot.unsupportedProtocol");
            }

            basePath = f.getAbsolutePath();
        }


        String getBasePath() {
            return basePath;
        }


        String getArchivePath() {
            return archivePath;
        }
    }
    
	@Override
	public void createWebResourceSet(ResourceSetType type, String webAppMount, String base, String archivePath,
			String internalPath) {
        List<WebResourceSet> resourceList;
        WebResourceSet resourceSet;

        switch (type) {
            case PRE:
                resourceList = preResources;
                break;
            case CLASSES_JAR:
                resourceList = classResources;
                break;
            case RESOURCE_JAR:
                resourceList = jarResources;
                break;
            case POST:
                resourceList = postResources;
                break;
            default:
                throw new IllegalArgumentException("standardRoot.createUnknownType");
        }

        // This implementation assumes that the base for all resources will be a
        // file.
        File file = new File(base);

        if (file.isFile()) {
            if (archivePath != null) {
                // Must be a JAR nested inside a WAR if archivePath is non-null
                resourceSet = new JarWarResourceSet(this, webAppMount, base,
                        archivePath, internalPath);
            } else if (file.getName().toLowerCase(Locale.ENGLISH).endsWith(".jar")) {
                resourceSet = new JarResourceSet(this, webAppMount, base,
                        internalPath);
            } else {
                resourceSet = new FileResourceSet(this, webAppMount, base,
                        internalPath);
            }
        } else if (file.isDirectory()) {
            resourceSet =
                    new DirResourceSet(this, webAppMount, base, internalPath);
        } else {
            throw new IllegalArgumentException("standardRoot.createInvalidFile");
        }

        if (type.equals(ResourceSetType.CLASSES_JAR)) {
            resourceSet.setClassLoaderOnly(true);
        } else if (type.equals(ResourceSetType.RESOURCE_JAR)) {
            resourceSet.setStaticOnly(true);
        }

        resourceList.add(resourceSet);
	}

	@Override
	public void addPreResources(WebResourceSet webResourceSet) {
		preResources.add(webResourceSet);
	}

	@Override
	public WebResourceSet[] getPreResources() {
		
		return null;
	}

	@Override
	public void addJarResources(WebResourceSet webResourceSet) {
		jarResources.add(webResourceSet);
	}

	@Override
	public WebResourceSet[] getJarResources() {
		
		return null;
	}

	@Override
	public void addPostResources(WebResourceSet webResourceSet) {
		postResources.add(webResourceSet);
	}

	@Override
	public WebResourceSet[] getPostResources() {
		
		return null;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void setAllowLinking(boolean allowLinking) {
		

	}

	@Override
	public boolean getAllowLinking() {
		
		return false;
	}

	@Override
	public void setCachingAllowed(boolean cachingAllowed) {
		

	}

	@Override
	public boolean isCachingAllowed() {
		
		return false;
	}

	@Override
	public void setCacheTtl(long ttl) {
		

	}

	@Override
	public long getCacheTtl() {
		
		return 0;
	}

	@Override
	public void setCacheMaxSize(long cacheMaxSize) {
		

	}

	@Override
	public long getCacheMaxSize() {
		
		return 0;
	}

	@Override
	public void setCacheObjectMaxSize(int cacheObjectMaxSize) {
		

	}

	@Override
	public int getCacheObjectMaxSize() {
		
		return 0;
	}

	@Override
	public void setTrackLockedFiles(boolean trackLockedFiles) {
		

	}

	@Override
	public boolean getTrackLockedFiles() {
		
		return false;
	}

	@Override
	public void backgroundProcess() {
		

	}

	@Override
	public void registerTrackedResource(TrackedWebResource trackedResource) {
		

	}

	@Override
	public void deregisterTrackedResource(TrackedWebResource trackedResource) {
		

	}

	@Override
	public List<URL> getBaseUrls() {
		
		return null;
	}

	@Override
	public void gc() {
		

	}

}
