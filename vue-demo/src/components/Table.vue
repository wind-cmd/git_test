<template>
  <div id="all">
    <!-- 查询表单 -->
    <div id="searchFrom">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="当前状态">
          <el-select v-model="formInline.status" placeholder="当前状态" clearable>
            <el-option label="启用" value="1" />
            <el-option label="禁用" value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="企业名称">
          <el-input v-model="formInline.brandName" placeholder="企业名称" clearable />
        </el-form-item>
        <el-form-item label="品牌名称">
          <el-input v-model="formInline.companyName" placeholder="品牌名称" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 按钮 -->
    <div id="btn">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" @click="handleDeleteAll">批量删除</el-button>
    </div>

    <!-- 新增弹窗 -->
    <el-dialog v-model="dialogFormVisible" :title="dialogType === 'add' ? '添加品牌' : '编辑品牌'" width="450px"
      :show-close="false">
      <el-form :model="form">
        <el-form-item label="品牌名称" :label-width="formLabelWidth">
          <el-input v-model="form.brandName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业名称" :label-width="formLabelWidth">
          <el-input v-model="form.companyName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="排序" :label-width="formLabelWidth">
          <el-input-number v-model="form.ordered" :min="1" :max="999" />
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="form.description" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" />
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-switch v-model="form.status" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmit">
            {{ dialogType === 'add' ? '新增' : '保存' }}
          </el-button>
          <el-button @click="handleCancle">
            返回
          </el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 表格 -->
    <div id="brandTable">
      <el-table :data="tableData" @selection-change="handleSelectionChange" border style="width: 100%">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="80" align="center" />
        <el-table-column prop="brandName" label="品牌名称" width="180" align="center" />
        <el-table-column prop="companyName" label="企业名称" width="180" align="center" />
        <el-table-column prop="ordered" label="排序" align="center" />
        <el-table-column prop="description" label="备注" header-align="center" />
        <el-table-column label="当前状态" width="100" align="center">
          <template #default="scope">
            {{ scope.row.status ? '启用' : '禁用' }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">
              修改
            </el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <div id="page">
      <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="pageSizes"
        :size="size" :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
        :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue';
import axios from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus'
import type { ComponentSize } from 'element-plus'

// 响应式数据
const dialogFormVisible = ref(false);//新增和编辑弹窗是否隐藏
const dialogType = ref('add'); // 'add' 或 'edit'
const formLabelWidth = 'auto';//表单label宽度
const tableData = ref([]);//表格展示数据
const allData = ref([]);//所有数据
const multipleSelection = ref([])
const ids = ref([]);//批量选中的数据id
//弹窗数据
const form = ref({
  id: null,
  brandName: '',
  companyName: '',
  ordered: 0,
  description: '',
  status: false,
});
// 查询条件
const formInline = reactive({
  status: '',
  brandName: '',
  companyName: '',
})
// 重置弹窗数据
const resetForm = () => {
  form.value = {
    id: null,
    brandName: '',
    companyName: '',
    ordered: 0,
    description: '',
    status: false,
  };
};
//分页数据
const currentPage = ref(1)//当前页数
const pageSize = ref(10)//每页显示条数
const size = ref<ComponentSize>('default')//分页组件尺寸
const background = ref(false)//是否启动背景阴影
const disabled = ref(false)//是否禁用分页组件
const total = ref(0)//总条数
const pageSizes = ref([10, 15, 20, 30, 50, 100])//每页显示条数选择器的选项

//page-size 改变时触发
const handleSizeChange = (val: number) => {
  currentPage.value = 1;
  tableData.value = allData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
}
//current-page 改变时触发
const handleCurrentChange = (val: number) => {
  tableData.value = allData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
}

// 生命周期钩子
onMounted(async () => {
  await fetchData();
});

// 获取全部数据
const fetchData = async () => {
  try {
    const { data } = await axios.post('/api/json/selectAll', formInline);
    for (let i = 0; i < data.length; i++) {
      data[i].status = data[i].status === 1;
    }
    allData.value = data;
    total.value = allData.value.length;
    tableData.value = allData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
  } catch (error) {
    console.error('获取数据失败:', error);
  }
};

//根据条件查询
const onSubmit = async () => {
  //下拉框查询条件clear后，status变为undefined，需要重置为''
  if (formInline.status == undefined) {
    formInline.status = '';
  }
  //根据条件查询数据
  fetchData();
};

// 新增按钮
const handleAdd = () => {
  //新增模式
  dialogType.value = 'add';
  resetForm();   //重置弹窗展示数据
  dialogFormVisible.value = true;
};

// 编辑按钮
const handleEdit = (row: any) => {
  // 编辑模式
  dialogType.value = 'edit';
  // 复制选中行的数据到表单
  form.value = { ...row };
  // 显示弹窗
  dialogFormVisible.value = true;
};

// 新增与提交公用一个弹窗，提交时判断是新增还是修改逻辑
const handleSubmit = async () => {
  try {
    if (dialogType.value === 'add') {//新增模式
      await axios.post('/api/json/addBrand', form.value);
      ElMessage.success('添加成功');
    } else {      // 编辑逻辑（需要传递 ID）
      await axios.post('/api/json/updateBrand', form.value);
      ElMessage.success('更新成功');
    }
    await fetchData(); // 刷新列表
    dialogFormVisible.value = false;//关闭弹窗
  } catch (error) {
    ElMessage.error('操作失败');
    console.error('提交失败:', error);
  }
};

//弹窗退出
const handleCancle = () => {
  //隐藏弹窗
  dialogFormVisible.value = false;
  //重置弹窗展示数据
  resetForm();
};

// 删除品牌
const handleDelete = async (row: any) => {
  ElMessageBox.confirm(
    '确认要删除该品牌吗？',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    //调用删除接口
    await axios.post('/api/json/deleteBrand', [row.id]);
    ElMessage({
      type: 'success',
      message: '删除成功',
    })
    await fetchData(); // 刷新列表
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '取消删除',
    })
  })
};

//批量删除操作
const handleDeleteAll = async () => {
  ElMessageBox.confirm(
    '确认要批量删除这些品牌吗？',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    if (ids.value == null || ids.value.length == 0) {
      ElMessage.error('请选择要删除的品牌');
      return;
    }
    try {
      await axios.post('/api/json/deleteBrand', ids.value);
      ElMessage.success('批量删除成功');
      await fetchData(); // 刷新列表
    } catch (error) {
      ElMessage.error('批量删除失败');
      console.error('批量删除失败:', error);
    }
  })
}
//获取复选框选取的数据
const handleSelectionChange = (val) => {
  multipleSelection.value = val;
  ids.value = multipleSelection.value.map((item) => item.id);
}




</script>

<style scoped>
#all {
  margin: 80px auto;
}

#btn {
  margin-bottom: 20px;
}

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>