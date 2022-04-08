<template>
  <div class="app-wrapper">
    <div class="navigation_sider">
      <div class="navigation_sider_title" @click="handleTitleClick">
        <h1 style="text-align:center">开发导航</h1>
      </div>

      <div class="navigation_sider_menu">
        <el-divider style="color:white"></el-divider>
        <div v-for="top in tabs" :key="top.id">
          <li><i class="el-icon-sugar"></i><a :href="'#' + top.id">{{top.label}}</a></li>
        </div>
      </div>
    </div>
    <div class="navigation_main">
      <div class="navigation_header">
        <!-- 智能化开发导航 -->
      </div>
      <div class="navigation_body">
        <div class="navigation_box" v-for="top in tabs" :key="top.id">
          <a href="#" :name="top.id"></a>
          <el-card>
            <div slot="header">
              <span>{{top.label}}</span>
            </div>
            <div v-show="top.navLinks.length" style="min-height: 200px;">
              <div v-for="navlink in top.navLinks" :key="navlink.id">
                <a target="_blank" :href="navlink.linkUrl">
                  <div class="item">
                    <div class="logo">
                      <el-image :src="navlink.icon" class="logo-img">
                        <!-- <div slot="error" class="image-slot">
                          <el-image src="http://img.duoziwang.com/2021/06/q101801413228587.jpg" class="logo-img" />
                        </div> -->
                      </el-image>
                      {{navlink.name}}
                    </div>
                    <div class="desc">{{navlink.intro}}</div>
                  </div>
                </a>
              </div>
            </div>
            <el-tabs v-show="!top.navLinks.length" style="min-height: 200px;">
              <el-tab-pane v-for="classify in top.children" :key="classify.id" :label="classify.label">
                <div v-for="navlink in classify.navLinks" :key="navlink.id">
                  <a target="_blank" :href="navlink.linkUrl">
                    <div class="item">
                      <div class="logo">
                        <i class="http://nofeel.design:8888/favicon.ico"></i>
                        <el-image :src="navlink.icon" class="logo-img">
                          <!-- <div slot="error" class="image-slot">
                            <el-image src="http://img.duoziwang.com/2021/06/q101801413228587.jpg" class="logo-img" />
                          </div> -->
                        </el-image>
                        {{navlink.name}}
                      </div>
                      <div class="desc">{{navlink.intro}}</div>
                    </div>
                  </a>
                </div>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { classifyTree } from '@/api/system/navigation/nav.js'
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.7.0",
      tabs: [],
      querys: {}
    };
  },
  methods: {
    handleTitleClick() {
      window.location = "/index";
    },
    initData() {
      console.log(this.querys);
      classifyTree(this.querys).then(response => {
        this.tabs = response.data;
      })
    },
    getUrl() {
      var url = window.location.search;
      //判断是否有参数？
      if (url.indexOf("?") != -1) {
        //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
        var str = url.substr(1);
        //用等号进行分隔 （只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
        let strs = str.split("=");
        this.querys.userKey = strs[1];
      }
      this.initData();
    }
  },
  mounted() {
    this.getUrl()

  }
};
</script>

<style scoped lang="scss">
  @import "~@/assets/styles/mixin.scss";
  @import "~@/assets/styles/variables.scss";

  .el-card__header {
    border-bottom: 1px solid #e6ebf5;
  }

  .navigation_sider {
    position: fixed;
    width: #{$base-sidebar-width};
    color: white;
    background-color: #4d4d4d;
    .navigation_sider_title {
      height: 60px;
    }
    .navigation_sider_menu {
      top: 60px;
      color: white;
      position: fixed;
      padding: 20px;
      margin-top: 10px;
      width: #{$base-sidebar-width};
      border-radius: 15px;
      height: 100%;
      background-color: #4d4d4d;
    }
  }

  .navigation_main {
    min-height: 100%;
    transition: margin-left 0.28s;
    margin-left: $base-sidebar-width;
    position: relative;
    background-color: #d9d9d9;
    min-height: 1080px;
  }

  .navigation_header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$base-sidebar-width});
    transition: width 0.28s;
  }

  .navigation_body {
    position: relative;
  }

  .navigation_box {
    margin: 0 0 10px 0;
    padding: 10px 60px 30px 60px;
  }

  .item {
    width: 16%;
    border: 1px solid #e4ecf3;
    box-shadow: 1px 2px 3px #f2f6f8;
    border-radius: 6px;
    background: #fff;
    padding: 10px;
    min-width: 200px;
    margin: 22px 1% 0 0;
    float: left;
    overflow: hidden;
    transition: all 0.3s;
    &:hover {
      transform: translateY(-5px);
    }
    .no-logo {
      color: #3273dc;
      font-weight: bold;
      font-size: 14px;
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

