<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="productListTemplate" type="x-tmpl-mustache">
{{#productList}}
 <tr role="row" class="material-name odd" data-id="{{id}}"><!--even -->
	<td>{{materialId}}</td>
	<td>{{materialMaterialname}}</td>
	<td>{{materialMaterialsource}}</td>
	<td>{{materialRealweight}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			<a class="btn btn-info product-edit" href="#" data-id="{{id}}" data-weight="{{materialRealweight}}">
				绑定
			</a>
		</div>
	</td>
</tr>
{{/productList}}
</script>