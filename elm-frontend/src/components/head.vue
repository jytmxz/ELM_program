<template>
    <div>
        <header>
            <slot></slot>
        </header>
    </div>
</template>

<script setup>

</script>

<style scoped>
.wrapper header {
	width: 100%;
	height: 12vw;
	background: #0097FF;
	font-size: 4.8vw;
	color: #FFFFFF;
	
	display: flex;
	justify-content: center;
	align-items: center;
	
	/*固定定位*/
	position: fixed;
	top: 0;
	left: 0;
}
</style>