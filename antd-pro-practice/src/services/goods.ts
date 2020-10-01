import request from '@/utils/request';
import {PageReqVo,PageVo} from "@/services/HttpCommonVo";

export class AdminGoodsPageReqVo extends PageReqVo {
  constructor(userId?: number) {
    super();
  }
}

export interface GoodsDO {
  id: number;
  userId: number;
  name: string;
}

export async function page(data: AdminGoodsPageReqVo): Promise<PageVo<GoodsDO>> {
  return request('/api/admin/goods/page', {
    method: 'POST',
    data
  });
}
