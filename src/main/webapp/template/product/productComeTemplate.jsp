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
	<td>{{materialImgid}}</td>
	<td>{{materialIrontype}}</td>
	<td>{{materialIrontypeweight}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td> 
	<td>{{materialRemark}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			<a class="blue product-edit" href="#" data-id="{{id}}">
				 <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/productList}}
</script>