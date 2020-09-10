import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/admin/goods/page',
    method: 'post',
    data
  })
}
