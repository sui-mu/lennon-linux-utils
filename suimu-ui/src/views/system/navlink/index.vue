<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="简介" prop="intro">
        <el-input v-model="queryParams.intro" placeholder="请输入简介" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="图标" prop="icon">
        <el-input v-model="queryParams.icon" placeholder="请输入图标" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input v-model="queryParams.sort" placeholder="请输入排序" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="链接状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择链接状态" clearable size="small">
          <el-option v-for="dict in dict.type.nav_link_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:navlink:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:navlink:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:navlink:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['system:navlink:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="navlinkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编码" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="简介" align="center" prop="intro" />
      <el-table-column label="图标" align="center" prop="icon" />
      <el-table-column label="链接" align="center" prop="linkUrl" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="链接状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.nav_link_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:navlink:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:navlink:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改链接对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="简介" prop="intro">
          <el-input v-model="form.intro" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入图标" />
        </el-form-item>
        <el-form-item label="链接" prop="linkUrl">
          <el-input v-model="form.linkUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="链接标签" prop="linkUrl">
          <el-tag :key="index" v-for="(tag,index) in form.tags" closable :disable-transitions="false" @close="handleTagRemove(tag)">
            {{tag.label}}
          </el-tag>
        </el-form-item>
        <el-form-item label="链接分类" prop="linkUrl">
          <el-input v-model="form.linkUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="链接状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择链接状态">
            <el-option v-for="dict in dict.type.nav_link_status" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
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
import { listNavlink, getNavlink, delNavlink, addNavlink, updateNavlink } from "@/api/system/navlink";

export default {
  name: "Navlink",
  dicts: ['nav_link_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 链接表格数据
      navlinkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        intro: null,
        icon: null,
        linkUrl: null,
        sort: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        intro: [
          { required: true, message: "简介不能为空", trigger: "blur" }
        ],
        linkUrl: [
          { required: true, message: "链接不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建者编码不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "更新者不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询链接列表 */
    getList() {
      this.loading = true;
      listNavlink(this.queryParams).then(response => {
        this.navlinkList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        name: null,
        intro: null,
        icon: null,
        linkUrl: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        sort: null,
        status: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加链接";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getNavlink(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改链接";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateNavlink(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNavlink(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除链接编号为"' + ids + '"的数据项？').then(function () {
        return delNavlink(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/navlink/export', {
        ...this.queryParams
      }, `navlink_${new Date().getTime()}.xlsx`)
    },
    handleTagRemove(tag) {
      console.log(tag)
      this.form.tags.splice(this.form.tags.indexOf(tag), 1);
    }
  }
};
</script>