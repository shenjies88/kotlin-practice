import request from '@/utils/request';
import {HttpCommonVo} from "@/services/HttpCommonVo";
import {LoginResultType} from "@/services/login";

export async function query(): Promise<any> {
  return request('/api/users');
}

export async function queryCurrent(): Promise<HttpCommonVo<LoginResultType>> {
  return request('/api/admin/user/my-info',{
    method: 'POST'
  });
}

export async function queryNotices(): Promise<any> {
  return request('/api/notices');
}
