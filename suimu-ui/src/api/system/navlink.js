import request from '@/utils/request'

// 查询链接列表
export function listNavlink(query) {
  return request({
    url: '/system/navlink/list',
    method: 'get',
    params: query
  })
}

// 查询链接详细
export function getNavlink(id) {
  return request({
    url: '/system/navlink/' + id,
    method: 'get'
  })
}

// 新增链接
export function addNavlink(data) {
  return request({
    url: '/system/navlink',
    method: 'post',
    data: data
  })
}

// 修改链接
export function updateNavlink(data) {
  return request({
    url: '/system/navlink',
    method: 'put',
    data: data
  })
}

// 删除链接
export function delNavlink(id) {
  return request({
    url: '/system/navlink/' + id,
    method: 'delete'
  })
}
