import request from '@/utils/request';
import {PageReqVo, PageVo} from "@/services/HttpCommonVo";

export class AdminUserPageReqVo extends PageReqVo {
}

export interface UserDO {
  id: number;
  account: string;
  nickname: string;
}

export async function query(): Promise<any> {
  return request('/api/users');
}

export async function queryCurrent(): Promise<HLoginResultType> {
  return request('/api/admin/user/my-info', {
    method: 'POST'
  });
}

export async function queryNotices(): Promise<any> {
  return request('/api/notices');
}

export async function page(data: AdminUserPageReqVo): Promise<PageVo<UserDO>> {
  return request('/api/admin/user/page', {
    method: 'POST',
    data
  });
}
