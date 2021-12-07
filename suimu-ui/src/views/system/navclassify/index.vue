<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="label">
        <el-input v-model="queryParams.label" placeholder="请输入名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input v-model="queryParams.sort" placeholder="请输入排序" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:navclassify:add']">新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="navclassifyList" row-key="id" default-expand-all :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column label="名称" prop="label" />
      <el-table-column label="父编码" align="center" prop="parentId" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.nav_classify_type" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-plus" @click="handleAdd(scope.row)" v-hasPermi="['system:navclassify:add']">新增</el-button>
          <el-button v-show="scope.row.share >= userLevel" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:navclassify:edit']">修改</el-button>
          <el-button v-show="scope.row.share >= userLevel" size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:navclassify:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="label">
          <el-input v-model="form.label" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="父编码" prop="parentId">
          <treeselect v-model="form.parentId" :options="navclassifyOptions" :normalizer="normalizer" placeholder="请选择父编码" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select disabled v-model="form.type" placeholder="请选择类型">
            <el-option v-for="dict in dict.type.nav_classify_type" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="状态" prop="share">
          <el-select v-model="form.share" placeholder="状态" clearable style="width: 100%">
            <el-option v-for="item in navclassifyStatusOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listNavclassify, getNavclassify, delNavclassify, addNavclassify, updateNavclassify } from "@/api/system/navclassify";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Navclassify",
  dicts: ['nav_classify_type'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 分类表格数据
      navclassifyList: [],
      // 分类树选项
      navclassifyOptions: [],
      // 分类状态
      navclassifyStatusOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        label: null,
        parentId: null,
        type: "1",
        sort: null,
        share: null,
        ancestors: null,
        deptId: null
      },
      // 用户等级
      userLevel: "",
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        label: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        parentId: [
          { required: true, message: "父编码不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    let roles = this.$store.getters.roles;
    roles.forEach(element => {
      if (element == 'admin') {
        this.userLevel = 1;
      } else {
        if (element == 'captain') {
          this.userLevel = 2;
        } else {
          this.userLevel = 3;
        }
      }
    });
    this.getDicts("nav_classify_status").then(response => {
      this.navclassifyStatusOptions = response.data.filter(item => item.dictValue >= this.userLevel);
    });
  },
  methods: {
    /** 查询分类列表 */
    getList() {
      this.loading = true;
      listNavclassify(this.queryParams).then(response => {
        this.navclassifyList = this.handleTree(response.data, "id", "parentId");
        this.loading = false;
      });
    },
    /** 转换分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.label,
        children: node.children
      };
    },
    /** 查询分类下拉树结构 */
    getTreeselect() {
      listNavclassify(this.queryParams).then(response => {
        this.navclassifyOptions = [];
        const data = { id: 0, label: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "parentId");
        this.navclassifyOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        label: null,
        parentId: null,
        type: "1",
        sort: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        share: null,
        ancestors: null,
        deptId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.id) {
        this.form.parentId = row.id;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.id;
      }
      getNavclassify(row.id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateNavclassify(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNavclassify(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除分类编号为"' + row.id + '"的数据项？').then(function () {
        return delNavclassify(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    }
  }
};
</script>