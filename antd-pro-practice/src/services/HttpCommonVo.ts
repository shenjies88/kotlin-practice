export interface HttpResultVo<T> {
  success: boolean;
  code: number;
  data?: T;
  errorMsg?: string;
}

export class PageReqVo {
  size: number = 10;
  num: number = 1;
}

export interface PageVo<T> {
  list: Array<T>;
  total: number;
}
