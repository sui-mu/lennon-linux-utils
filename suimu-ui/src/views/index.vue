<template>
  <div class="app-container home">
    <el-container>
      <el-header>
        <div>
          智能化开发导航
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <div class="left-bar">
            <div v-for="top in tabs" :key="top.id">
              <a :href="'#' + top.id" class="active"><i class="iconfont icon-shequ"></i>{{top.label}}</a>
            </div>
          </div>
        </el-aside>
        <el-main>
          <div class="main">
            <div class="box" v-for="top in tabs" :key="top.id">
              <a href="#" :name="top.id"></a>
              <el-card>
                <div slot="header" class="clearfix">
                  <span>{{top.label}}</span>
                </div>
                <div v-show="top.navLinks.length" style="min-height: 200px;">
                  <div v-for="navlink in top.navLinks" :key="navlink.id">
                    <a target="_blank" href="navlink.linkUrl">
                      <div class="item">
                        <div class="logo"><img :src="navlink.icon" alt="CNDS">{{navlink.name}}</div>
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
                          <div class="logo"><img :src="navlink.icon" alt="CNDS">{{navlink.name}}</div>
                          <div class="desc">{{navlink.intro}}</div>
                        </div>
                      </a>
                    </div>
                  </el-tab-pane>
                </el-tabs>
              </el-card>
            </div>
          </div>

        </el-main>
      </el-container>
    </el-container>
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
      tabs: []
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
  mounted() {
    classifyTree().then(response => {
      this.tabs = response.data;
    })
  }
};
</script>

<style scoped lang="scss">
  .el-aside {
    background-color: white;
    color: #333;
    // text-align: center;
  }
  .top-bar {
    position: fixed;
  }
  .left-bar {
    position: fixed;
    // background: #30333c;
    // color: #6b7386;
    box-sizing: border-box;
    flex-direction: column;
    height: 100vh;
    display: flex;
    width: 248px;
    transition: all 0.5s;
  }
  .main {
    position: relative;
    display: flex;
    flex-direction: column;
    -webkit-box-orient: vertical;
    // margin-left: 200px;
    .box {
      overflow: hidden;
      margin: 0px 0px 10px 0px;
      background: #fff;
    }
    .item {
      width: 20%;
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
        img {
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
  }
</style>

