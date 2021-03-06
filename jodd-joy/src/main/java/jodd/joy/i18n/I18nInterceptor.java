// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.joy.i18n;

import jodd.madvoc.ActionRequest;
import jodd.madvoc.interceptor.ActionInterceptor;
import jodd.proxetta.ProxettaUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Prepares bundle name for current request.
 */
public class I18nInterceptor implements ActionInterceptor {

	@Override
	public Object intercept(final ActionRequest actionRequest) throws Exception {
		HttpServletRequest request = actionRequest.getHttpServletRequest();

		// defines request bundle of this http request
		LocalizationUtil.setRequestBundleName(request, getActionClassName(actionRequest.getAction()));

		return actionRequest.invoke();
	}

	/**
	 * Returns correct action class name. Detects Proxetta classes.
	 */
	protected String getActionClassName(final Object action) {
		Class clazz = action.getClass();

		clazz = ProxettaUtil.getTargetClass(clazz);

		return clazz.getName();
	}

}
