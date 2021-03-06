import request from '@/utils/request';

export interface LoginParamsType {
  account: string;
  pwd: string;
}

export interface LoginResultType {
  id: number;
  account: string;
  token: string;
}

export async function fakeAccountLogin(params: LoginParamsType): Promise<LoginResultType> {
  return request('/api/admin/authentication/login', {
    method: 'POST',
    data: params,
  });
}

export async function getFakeCaptcha(mobile: string) {
  return request(`/api/login/captcha?mobile=${mobile}`);
}

export async function accountLogout() {
  return request(`/api/admin/authentication/logout`, {
    method: 'POST'
  });
}
