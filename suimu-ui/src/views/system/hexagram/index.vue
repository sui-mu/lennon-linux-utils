<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="卦" prop="id">
                <el-input v-model="queryParams.id" placeholder="请输入卦" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="卦名" prop="name">
                <el-input v-model="queryParams.name" placeholder="请输入卦名" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="简述" prop="info">
                <el-input v-model="queryParams.info" placeholder="请输入简述" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="卦形" prop="shape">
                <el-input v-model="queryParams.shape" placeholder="请输入卦形" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="标志" prop="tag">
                <el-input v-model="queryParams.tag" placeholder="请输入标志" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['system:hexagram:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['system:hexagram:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
                    v-hasPermi="['system:hexagram:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['system:hexagram:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="hexagramList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="卦名" align="center" prop="name" />
            <el-table-column label="卦形" align="center" prop="shape" />
            <el-table-column label="标志" align="center" prop="tag" />
            <el-table-column label="卦象" align="center" prop="image" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['system:hexagram:edit']">修改</el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['system:hexagram:remove']">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />

        <!-- 添加或修改卦对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="卦词" prop="id">
                    <el-input v-model="form.id" placeholder="请输入卦词" maxlength="6" show-word-limit />
                </el-form-item>
                <el-form-item label="卦名" prop="name">
                    <el-input v-model="form.name" placeholder="请输入卦名" />
                </el-form-item>
                <el-form-item label="卦形" prop="shape">
                    <el-input v-model="form.shape" placeholder="请输入卦形" />
                </el-form-item>
                <el-form-item label="标志" prop="tag">
                    <el-input v-model="form.tag" placeholder="请输入标志" />
                </el-form-item>
                <el-form-item label="简述" prop="info">
                    <el-input v-model="form.info" placeholder="请输入简述" type="textarea" />
                </el-form-item>
                <el-form-item label="卦象">
                    <imageUpload v-model="form.image" />
                </el-form-item>
                <el-form-item label="内容">
                    <editor v-model="form.content" :min-height="192" />
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
import { listHexagram, getHexagram, delHexagram, addHexagram, updateHexagram } from "@/api/system/hexagram";

export default {
    name: "Hexagram",
    data() {
        return {
            // 操作类型
            operType: "",
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
            // 卦表格数据
            hexagramList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                id: null,
                name: null,
                info: null,
                image: null,
                content: null,
                shape: null,
                tag: null
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                id: [
                    { required: true, message: "卦象不能为空", trigger: "blur" }
                ],
                name: [
                    { required: true, message: "卦名不能为空", trigger: "blur" }
                ],
                info: [
                    { required: true, message: "简述不能为空", trigger: "blur" }
                ],
                shape: [
                    { required: true, message: "卦形不能为空", trigger: "blur" }
                ],
                tag: [
                    { required: true, message: "标志不能为空", trigger: "blur" }
                ]
            }
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询卦列表 */
        getList() {
            this.loading = true;
            listHexagram(this.queryParams).then(response => {
                this.hexagramList = response.rows;
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
                info: null,
                image: null,
                content: null,
                shape: null,
                tag: null
            };
            this.resetForm("form");
            this.operType = "";
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
            this.operType = "insert";
            this.open = true;
            this.title = "添加卦";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids
            getHexagram(id).then(response => {
                this.form = response.data;
                this.operType = "update";
                this.open = true;
                this.title = "修改卦";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if ("update" === this.operType) {
                        updateHexagram(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addHexagram(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除卦编号为"' + ids + '"的数据项？').then(function () {
                return delHexagram(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('system/hexagram/export', {
                ...this.queryParams
            }, `hexagram_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>