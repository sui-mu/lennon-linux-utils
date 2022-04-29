<template>
  <div>
    <a target="_blank" :href="linkItem.linkUrl">
      <div class="item">
        <el-row>
          <div class="logo">
            <el-col :span="6">
              <el-image :src="linkItem.icon" class="logo-img" />
            </el-col>
            <el-col :span="8">
              <div class="item-title">{{linkItem.name}}</div>
            </el-col>
          </div>
        </el-row>
        <el-row>
          <div class="desc">{{linkItem.intro}}</div>
        </el-row>
      </div>
    </a>
  </div>
</template>

<script>


export default {
  name: "LinkItem",
  data() {
    return {
      // 版本号
      version: "3.7.0",
      tabs: [],
      querys: {}
    };
  },
  props: {
    linkItem: {
      type: Object
    }
  }
}
</script>

<style lang="scss"scoped>
  .item {
    width: 15.6677%;
    border: 1px solid #e4ecf3;
    box-shadow: 1px 2px 3px #f2f6f8;
    border-radius: 6px;
    background: #fff;
    padding: 10px;
    min-width: 10%;
    margin: 0% 1% 1% 0%;
    float: left;
    overflow: hidden;
    transition: all 0.3s;
    &:hover {
      transform: translateY(-5px);
    }
    .item-title {
      color: #3273dc;
      line-height: 1.7;
      font-size: 18px;
    }
    .logo {
      height: 40px;
      position: relative;
      font-size: 14px;
      font-weight: 700;
      color: #3273dc;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      padding: 0 0.1rem;
      .logo-img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        vertical-align: middle;
      }
    }
    .desc {
      color: gray;
      font-size: 12px;
      padding-top: 10px;
      height: 35px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
    }
  }
</style>