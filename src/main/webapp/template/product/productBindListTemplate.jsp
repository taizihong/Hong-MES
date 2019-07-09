<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="productListTemplate" type="x-tmpl-mustache">
{{#productList}}
 <tr role="row" class="material-name odd" data-id="{{id}}"><!--even -->
	<td>{{materialId}}</td>
	<td>{{parentPro.materialId}}</td>
	<td>{{materialMaterialname}}</td>
	<td>{{materialMaterialsource}}</td>
	<td>{{materialTargetweight}}</td>
	<td>{{materialRealweight}}</td>
	<td>{{materialLeftweight}}</td>
	<td>{{materialImgid}}</td>
	<td>{{materialIrontype}}</td>
	<td>{{materialIrontypeweight}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td> 
	<td>{{materialRemark}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			<a class="btn blue product-edit" href="#" data-id="{{id}}" data-weight="{{materialLeftweight}}">
				 点击绑定
			</a>
		</div>
	</td>
</tr>
{{/productList}}
</script>