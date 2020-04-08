package com.quakearts.approvalengine.context;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.approvalengine.services.impl.ApprovalContextBuilderImpl;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;

/**Injectable factory instance for creating {@linkplain ApprovalContextBuilder} instances
 * @author kwaku
 *
 */
@Singleton
public class ApprovalContextBuilderFactory {
	private ApprovalContextBuilderFactory(){}

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory factory;

	/**Create a {@linkplain ApprovalContextBuilder} instance
	 * @return the instance
	 */
	public ApprovalContextBuilder createApprovalContext() {
		return new ApprovalContextBuilderImpl(factory.getDataStore());
	}
}
