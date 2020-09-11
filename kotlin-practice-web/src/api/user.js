import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/authentication/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/admin/user/my-info',
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/admin/authentication/logout',
    method: 'post'
  })
}

export function page(data) {
  return request({
    url: '/admin/user/page',
    method: 'post',
    data
  })
}
