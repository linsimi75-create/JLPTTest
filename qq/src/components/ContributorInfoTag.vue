<template>
  <div class="contributor-info-tag">
    <slot />
  </div>
</template>

<script lang="ts" setup>
// 无需props，内容通过slot传递
</script>

<style scoped lang="less">
.contributor-info-tag {
  position: relative;
  display: flex;
  align-items: center;
  background: #23272f;
  color: #e0e0e0;
  font-size: 12px;
  border-radius: 4px;
  padding: 2px 4px;
  overflow: hidden;
  box-shadow: 0 2px 8px 0 #181a1b33;
  min-width: 60px;
  min-height: 22px;
  &::before {
    content: '';
    position: absolute;
    left: -60%;
    top: 0;
    width: 60%;
    height: 100%;
    background: linear-gradient(120deg, transparent 0%, #3a3f4b 40%, #8ecfff 60%, #3a3f4b 80%, transparent 100%);
    opacity: 0.35;
    transform: skewX(-20deg);
    animation: sweep-dark 2.2s infinite linear;
    pointer-events: none;
  }
}
@keyframes sweep-dark {
  0% {
    left: -60%;
  }
  100% {
    left: 120%;
  }
}
</style>
