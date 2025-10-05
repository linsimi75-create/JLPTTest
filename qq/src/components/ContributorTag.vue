<template>
  <span class="contributor-tag">
    <slot>贡献者</slot>
  </span>
</template>

<script lang="ts" setup>
// 无需props，内容通过slot传递
</script>

<style scoped lang="less">
.contributor-tag {
  display: inline-block;
  border-radius: 4px;
  padding: 2px 6px;
  margin-right: 8px;
  background: rgba(30,30,30,0.7);
  color: #8ecfff;
  font-size: 12px;
}
</style>
