import request from '@/utils/request'

// 查询链接分类关系列表
export function listNavclrel(query) {
  return request({
    url: '/system/navclrel/list',
    method: 'get',
    params: query
  })
}

// 查询链接分类关系详细
export function getNavclrel(linkId) {
  return request({
    url: '/system/navclrel/' + linkId,
    method: 'get'
  })
}

// 新增链接分类关系
export function addNavclrel(data) {
  return request({
    url: '/system/navclrel',
    method: 'post',
    data: data
  })
}

// 修改链接分类关系
export function updateNavclrel(data) {
  return request({
    url: '/system/navclrel',
    method: 'put',
    data: data
  })
}

// 删除链接分类关系
export function delNavclrel(linkId) {
  return request({
    url: '/system/navclrel/' + linkId,
    method: 'delete'
  })
}
