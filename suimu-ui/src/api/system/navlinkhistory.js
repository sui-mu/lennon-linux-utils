import request from '@/utils/request'

// 查询历史记录列表
export function listNavlinkhistory(query) {
  return request({
    url: '/system/navlinkhistory/list',
    method: 'get',
    params: query
  })
}

// 查询历史记录详细
export function getNavlinkhistory(id) {
  return request({
    url: '/system/navlinkhistory/' + id,
    method: 'get'
  })
}

// 新增历史记录
export function addNavlinkhistory(data) {
  return request({
    url: '/system/navlinkhistory',
    method: 'post',
    data: data
  })
}

// 修改历史记录
export function updateNavlinkhistory(data) {
  return request({
    url: '/system/navlinkhistory',
    method: 'put',
    data: data
  })
}

// 删除历史记录
export function delNavlinkhistory(id) {
  return request({
    url: '/system/navlinkhistory/' + id,
    method: 'delete'
  })
}
