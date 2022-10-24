<header class="p-3 mb-3 border-bottom">
	<div class="container">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
				<img src="/images/dailyhub.png" alt="dailyhub" height="38px">
			</a>

			<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
			    style="margin: 0 auto;">
				<li><a href="/" class="nav-link px-2 link-secondary ">我的收藏</a></li>
				<li><a href="/collect-square" class="nav-link px-2 link-secondary">收藏广场</a></li>
				<li><a href="#" class="nav-link px-2 link-secondary">IDEA破解</a></li>
			</ul>

			<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="/search" method="get">
				<input type="hidden" name="userId" value="${userId}">
				<input name="q" id="keyword" type="search" class="form-control" placeholder="搜索..."
				       aria-label="Search" value="${q}">
			</form>


            <#if current.id == -1>
				<!--未登录-->
				<a class="btn btn-primary" href="login.html" role="button">登录</a>

            <#else>
				<!--已登录-->
				<div class="dropdown text-end">
					<a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
					   data-bs-toggle="dropdown" aria-expanded="false">
						<img src="${current.avatar}"
						     alt="mdo" width="32" height="32" class="rounded-circle">
						<span>${current.username}</span>
					</a>
					<ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
						<li><a class="dropdown-item" href="/collect/edit">新建收藏</a></li>
						<li><a class="dropdown-item" href="#">个人中心</a></li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item" href="/logout">注销</a></li>
					</ul>
				</div>
            </#if>
		</div>
	</div>
</header>