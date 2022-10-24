<#macro layout title>
	<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>我的收藏 - dailyhub</title>

		<link rel="stylesheet" href="/layui/css/layui.css">
		<link href="/css/bootstrap.min.css" rel="stylesheet">
		<link href="/css/sidebars.css" rel="stylesheet">
		<link href="/css/headers.css" rel="stylesheet">
		<script src="/js/jquery-3.6.0.min.js"></script>
		<script src="/js/bootstrap.bundle.min.js"></script>
		<script src="/layui/layui.all.js"></script>

		<style type="text/css">
			[v-cloak] {
				display: none !important;
			}
		</style>
		<script>
			var _hmt = _hmt || [];
			(function() {
				var hm = document.createElement("script");
				hm.src = "https://hm.baidu.com/hm.js?7a8ec283acf8e35ae729db292611eeca";
				var s = document.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(hm, s);
			})();
		</script>


	</head>
	<body>
	<div class="container" style="max-width: 960px;">

		<#include "/inc/header.ftl" />

		<#nested >

	</div>

<#--	<script>-->

<#--		$(function () {-->

<#--			layui.config({-->
<#--				version: false-->
<#--				, debug: false-->
<#--				, base: ''-->
<#--			});-->
<#--		});-->
<#--	</script>-->
	</body>
	</html>

</#macro>