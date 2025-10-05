<template>

    <div class="HomeView">
     

        <div class="HomeView_Content">
            <ScrollCard />
        </div>
    </div>

</template>

<script lang="ts" setup>
import { ref, onMounted , onUnmounted } from 'vue';
import ScrollCard from '@/components/ScrollCard/index.vue';





</script>

<style lang="less" scoped>
.HomeView {
    .HomeView_Nav_Menu {
        height: 120px;

        display: flex;
        align-items: center;
        padding: 0 24px;
    }
   
}
</style>
