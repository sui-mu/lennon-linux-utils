import request from '@/utils/request'

// 查询卦列表
export function listHexagram(query) {
  return request({
    url: '/system/hexagram/list',
    method: 'get',
    params: query
  })
}

// 查询卦详细
export function getHexagram(id) {
  return request({
    url: '/system/hexagram/' + id,
    method: 'get'
  })
}

// 新增卦
export function addHexagram(data) {
  return request({
    url: '/system/hexagram',
    method: 'post',
    data: data
  })
}

// 修改卦
export function updateHexagram(data) {
  return request({
    url: '/system/hexagram',
    method: 'put',
    data: data
  })
}

// 删除卦
export function delHexagram(id) {
  return request({
    url: '/system/hexagram/' + id,
    method: 'delete'
  })
}
