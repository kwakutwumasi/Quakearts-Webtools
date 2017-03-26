package org.jboss.gravel.data.converter;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

/**
 *
 */
public final class BreakingEscapeConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("getAsObject not supported");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        final String orig = value.toString();
        final int origLength = orig.length();
        final StringBuilder builder = new StringBuilder(origLength);
        for (int i = 0; i < origLength; i++) {
            final int c = orig.codePointAt(i);
            if (Character.isHighSurrogate(orig.charAt(i))) {
                i++;
            }
            switch (c) {
                case 10: builder.append("<br/>"); break;
                case 13: /* nothing */ break;
                case 34: builder.append("&quot;"); break;
                case 38: builder.append("&amp;"); break;
                case 60: builder.append("&lt;"); break;
                case 62: builder.append("&gt;"); break;
                case 161: builder.append("&iexcl;"); break;
                case 162: builder.append("&cent;"); break;
                case 163: builder.append("&pound;"); break;
                case 164: builder.append("&curren;"); break;
                case 165: builder.append("&yen;"); break;
                case 166: builder.append("&brvbar;"); break;
                case 167: builder.append("&sect;"); break;
                case 168: builder.append("&uml;"); break;
                case 169: builder.append("&copy;"); break;
                case 170: builder.append("&ordf;"); break;
                case 171: builder.append("&laquo;"); break;
                case 172: builder.append("&not;"); break;
                case 173: builder.append("&shy;"); break;
                case 174: builder.append("&reg;"); break;
                case 175: builder.append("&macr;"); break;
                case 176: builder.append("&deg;"); break;
                case 177: builder.append("&plusmn;"); break;
                case 178: builder.append("&sup2;"); break;
                case 179: builder.append("&sup3;"); break;
                case 180: builder.append("&acute;"); break;
                case 181: builder.append("&micro;"); break;
                case 182: builder.append("&para;"); break;
                case 183: builder.append("&middot;"); break;
                case 184: builder.append("&cedil;"); break;
                case 185: builder.append("&sup1;"); break;
                case 186: builder.append("&ordm;"); break;
                case 187: builder.append("&raquo;"); break;
                case 188: builder.append("&frac14;"); break;
                case 189: builder.append("&frac12;"); break;
                case 190: builder.append("&frac34;"); break;
                case 191: builder.append("&iquest;"); break;
                case 192: builder.append("&Agrave;"); break;
                case 193: builder.append("&Aacute;"); break;
                case 194: builder.append("&Acirc;"); break;
                case 195: builder.append("&Atilde;"); break;
                case 196: builder.append("&Auml;"); break;
                case 197: builder.append("&Aring;"); break;
                case 198: builder.append("&AElig;"); break;
                case 199: builder.append("&Ccedil;"); break;
                case 200: builder.append("&Egrave;"); break;
                case 201: builder.append("&Eacute;"); break;
                case 202: builder.append("&Ecirc;"); break;
                case 203: builder.append("&Euml;"); break;
                case 204: builder.append("&Igrave;"); break;
                case 205: builder.append("&Iacute;"); break;
                case 206: builder.append("&Icirc;"); break;
                case 207: builder.append("&Iuml;"); break;
                case 208: builder.append("&ETH;"); break;
                case 209: builder.append("&Ntilde;"); break;
                case 210: builder.append("&Ograve;"); break;
                case 211: builder.append("&Oacute;"); break;
                case 212: builder.append("&Ocirc;"); break;
                case 213: builder.append("&Otilde;"); break;
                case 214: builder.append("&Ouml;"); break;
                case 215: builder.append("&times;"); break;
                case 216: builder.append("&Oslash;"); break;
                case 217: builder.append("&Ugrave;"); break;
                case 218: builder.append("&Uacute;"); break;
                case 219: builder.append("&Ucirc;"); break;
                case 220: builder.append("&Uuml;"); break;
                case 221: builder.append("&Yacute;"); break;
                case 222: builder.append("&THORN;"); break;
                case 223: builder.append("&szlig;"); break;
                case 224: builder.append("&agrave;"); break;
                case 225: builder.append("&aacute;"); break;
                case 226: builder.append("&acirc;"); break;
                case 227: builder.append("&atilde;"); break;
                case 228: builder.append("&auml;"); break;
                case 229: builder.append("&aring;"); break;
                case 230: builder.append("&aelig;"); break;
                case 231: builder.append("&ccedil;"); break;
                case 232: builder.append("&egrave;"); break;
                case 233: builder.append("&eacute;"); break;
                case 234: builder.append("&ecirc;"); break;
                case 235: builder.append("&euml;"); break;
                case 236: builder.append("&igrave;"); break;
                case 237: builder.append("&iacute;"); break;
                case 238: builder.append("&icirc;"); break;
                case 239: builder.append("&iuml;"); break;
                case 240: builder.append("&eth;"); break;
                case 241: builder.append("&ntilde;"); break;
                case 242: builder.append("&ograve;"); break;
                case 243: builder.append("&oacute;"); break;
                case 244: builder.append("&ocirc;"); break;
                case 245: builder.append("&otilde;"); break;
                case 246: builder.append("&ouml;"); break;
                case 247: builder.append("&divide;"); break;
                case 248: builder.append("&oslash;"); break;
                case 249: builder.append("&ugrave;"); break;
                case 250: builder.append("&uacute;"); break;
                case 251: builder.append("&ucirc;"); break;
                case 252: builder.append("&uuml;"); break;
                case 253: builder.append("&yacute;"); break;
                case 254: builder.append("&thorn;"); break;
                case 255: builder.append("&yuml;"); break;
                case 338: builder.append("&OElig;"); break;
                case 339: builder.append("&oelig;"); break;
                case 352: builder.append("&Scaron;"); break;
                case 353: builder.append("&scaron;"); break;
                case 376: builder.append("&Yuml;"); break;
                case 402: builder.append("&fnof;"); break;
                case 710: builder.append("&circ;"); break;
                case 732: builder.append("&tilde;"); break;
                case 913: builder.append("&Alpha;"); break;
                case 914: builder.append("&Beta;"); break;
                case 915: builder.append("&Gamma;"); break;
                case 916: builder.append("&Delta;"); break;
                case 917: builder.append("&Epsilon;"); break;
                case 918: builder.append("&Zeta;"); break;
                case 919: builder.append("&Eta;"); break;
                case 920: builder.append("&Theta;"); break;
                case 921: builder.append("&Iota;"); break;
                case 922: builder.append("&Kappa;"); break;
                case 923: builder.append("&Lambda;"); break;
                case 924: builder.append("&Mu;"); break;
                case 925: builder.append("&Nu;"); break;
                case 926: builder.append("&Xi;"); break;
                case 927: builder.append("&Omicron;"); break;
                case 928: builder.append("&Pi;"); break;
                case 929: builder.append("&Rho;"); break;
                case 931: builder.append("&Sigma;"); break;
                case 932: builder.append("&Tau;"); break;
                case 933: builder.append("&Upsilon;"); break;
                case 934: builder.append("&Phi;"); break;
                case 935: builder.append("&Chi;"); break;
                case 936: builder.append("&Psi;"); break;
                case 937: builder.append("&Omega;"); break;
                case 946: builder.append("&beta;"); break;
                case 947: builder.append("&gamma;"); break;
                case 948: builder.append("&delta;"); break;
                case 949: builder.append("&epsilon;"); break;
                case 950: builder.append("&zeta;"); break;
                case 951: builder.append("&eta;"); break;
                case 952: builder.append("&theta;"); break;
                case 953: builder.append("&iota;"); break;
                case 954: builder.append("&kappa;"); break;
                case 955: builder.append("&lambda;"); break;
                case 956: builder.append("&mu;"); break;
                case 957: builder.append("&nu;"); break;
                case 958: builder.append("&xi;"); break;
                case 959: builder.append("&omicron;"); break;
                case 960: builder.append("&pi;"); break;
                case 961: builder.append("&rho;"); break;
                case 962: builder.append("&sigmaf;"); break;
                case 963: builder.append("&sigma;"); break;
                case 964: builder.append("&tau;"); break;
                case 965: builder.append("&upsilon;"); break;
                case 966: builder.append("&phi;"); break;
                case 967: builder.append("&chi;"); break;
                case 968: builder.append("&psi;"); break;
                case 969: builder.append("&omega;"); break;
                case 977: builder.append("&thetasym;"); break;
                case 978: builder.append("&upsih;"); break;
                case 982: builder.append("&piv;"); break;
                case 8194: builder.append("&ensp;"); break;
                case 8195: builder.append("&emsp;"); break;
                case 8201: builder.append("&thinsp;"); break;
                case 8204: builder.append("&zwnj;"); break;
                case 8205: builder.append("&zwj;"); break;
                case 8206: builder.append("&lrm;"); break;
                case 8207: builder.append("&rlm;"); break;
                case 8211: builder.append("&ndash;"); break;
                case 8212: builder.append("&mdash;"); break;
                case 8216: builder.append("&lsquo;"); break;
                case 8217: builder.append("&rsquo;"); break;
                case 8218: builder.append("&sbquo;"); break;
                case 8220: builder.append("&ldquo;"); break;
                case 8221: builder.append("&rdquo;"); break;
                case 8222: builder.append("&bdquo;"); break;
                case 8224: builder.append("&dagger;"); break;
                case 8225: builder.append("&Dagger;"); break;
                case 8226: builder.append("&bull;"); break;
                case 8230: builder.append("&hellip;"); break;
                case 8240: builder.append("&permil;"); break;
                case 8242: builder.append("&prime;"); break;
                case 8243: builder.append("&Prime;"); break;
                case 8249: builder.append("&lsaquo;"); break;
                case 8250: builder.append("&rsaquo;"); break;
                case 8254: builder.append("&oline;"); break;
                case 8260: builder.append("&frasl;"); break;
                case 8364: builder.append("&euro;"); break;
                case 8465: builder.append("&image;"); break;
                case 8472: builder.append("&weierp;"); break;
                case 8476: builder.append("&real;"); break;
                case 8482: builder.append("&trade;"); break;
                case 8501: builder.append("&alefsym;"); break;
                case 8592: builder.append("&larr;"); break;
                case 8593: builder.append("&uarr;"); break;
                case 8594: builder.append("&rarr;"); break;
                case 8595: builder.append("&darr;"); break;
                case 8596: builder.append("&harr;"); break;
                case 8629: builder.append("&crarr;"); break;
                case 8656: builder.append("&lArr;"); break;
                case 8657: builder.append("&uArr;"); break;
                case 8658: builder.append("&rArr;"); break;
                case 8659: builder.append("&dArr;"); break;
                case 8660: builder.append("&hArr;"); break;
                case 8704: builder.append("&forall;"); break;
                case 8706: builder.append("&part;"); break;
                case 8707: builder.append("&exist;"); break;
                case 8709: builder.append("&empty;"); break;
                case 8711: builder.append("&nabla;"); break;
                case 8712: builder.append("&isin;"); break;
                case 8713: builder.append("&notin;"); break;
                case 8715: builder.append("&ni;"); break;
                case 8719: builder.append("&prod;"); break;
                case 8721: builder.append("&sum;"); break;
                case 8722: builder.append("&minus;"); break;
                case 8727: builder.append("&lowast;"); break;
                case 8730: builder.append("&radic;"); break;
                case 8733: builder.append("&prop;"); break;
                case 8734: builder.append("&infin;"); break;
                case 8736: builder.append("&ang;"); break;
                case 8743: builder.append("&and;"); break;
                case 8744: builder.append("&or;"); break;
                case 8745: builder.append("&cap;"); break;
                case 8746: builder.append("&cup;"); break;
                case 8747: builder.append("&int;"); break;
                case 8756: builder.append("&there4;"); break;
                case 8764: builder.append("&sim;"); break;
                case 8773: builder.append("&cong;"); break;
                case 8776: builder.append("&asymp;"); break;
                case 8800: builder.append("&ne;"); break;
                case 8801: builder.append("&equiv;"); break;
                case 8804: builder.append("&le;"); break;
                case 8805: builder.append("&ge;"); break;
                case 8834: builder.append("&sub;"); break;
                case 8835: builder.append("&sup;"); break;
                case 8836: builder.append("&nsub;"); break;
                case 8838: builder.append("&sube;"); break;
                case 8839: builder.append("&supe;"); break;
                case 8853: builder.append("&oplus;"); break;
                case 8855: builder.append("&otimes;"); break;
                case 8869: builder.append("&perp;"); break;
                case 8901: builder.append("&sdot;"); break;
                case 8968: builder.append("&lceil;"); break;
                case 8969: builder.append("&rceil;"); break;
                case 8970: builder.append("&lfloor;"); break;
                case 8971: builder.append("&rfloor;"); break;
                case 9001: builder.append("&lang;"); break;
                case 9002: builder.append("&rang;"); break;
                case 9674: builder.append("&loz;"); break;
                case 9824: builder.append("&spades;"); break;
                case 9827: builder.append("&clubs;"); break;
                case 9829: builder.append("&hearts;"); break;
                case 9830: builder.append("&diams;"); break;
                default:
                    if (0x20 <= c && c < 0xa0) {
                        builder.appendCodePoint(c);
                    } else {
                        addEntity(c, builder);
                    }
            }
        }
        // sort of a shame to turn it into a string after all that, but...
        return builder.toString();
    }

    /**
     * Write an entity value.
     * @param code the code to write
     * @param builder the builder to append to
     */
    private static void addEntity(int code, StringBuilder builder) {
        builder.append('&').append('#');
        if (code > 100000) {
            builder.append((char)('0' + code / 100000)).append((char)('0' + (code % 100000) / 10000))
                .append((char)('0' + (code % 10000) / 1000)).append((char)('0' + (code % 1000) / 100))
                .append((char)('0' + (code % 100) / 10)).append((char)('0' + code % 10));
        } else if (code > 10000) {
            builder.append((char)('0' + code / 10000)).append((char)('0' + (code % 10000) / 1000))
                .append((char)('0' + (code % 1000) / 100)).append((char)('0' + (code % 100) / 10))
                .append((char)('0' + code % 10));
        } else if (code > 1000) {
            builder.append((char)('0' + code / 1000)).append((char)('0' + (code % 1000) / 100))
                .append((char)('0' + (code % 100) / 10)).append((char)('0' + code % 10));
        } else if (code > 100) {
            builder.append((char)('0' + code / 100)).append((char)('0' + (code % 100) / 10))
                .append((char)('0' + code % 10));
        } else if (code > 10) {
            builder.append((char)('0' + code / 10)).append((char)('0' + code % 10));
        } else {
            builder.append((char)('0' + code));
        }
        builder.append(';');
    }

}
