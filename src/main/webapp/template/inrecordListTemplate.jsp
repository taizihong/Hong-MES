<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="inrecordListTemplate" type="x-tmpl-mustache">
{{#inrecordList}}
 <tr role="row" class="inrecord-name odd" data-id="{{id}}"><!--even -->
	<td><input type="checkbox" data-id="{{id}}"/></td>
	<td>{{mesStorage.storageName}}</a></td>
	<td>{{mesProduct.materialId}}</td>
	<td>{{mesProduct.materialImgid}}</td>
	<td>{{mesProduct.materialMaterialname}}</td>
	<td>{{mesProduct.materialMaterialsource}}</td>
	<td>{{mesProduct.materialRealweight}}</td>
	<td>{{mesProduct.materialLeftweight}}</td>
	<td>{{mesProduct.materialIrontypeweight}}</td>
	<td>{{inCheck}}</td>
	<td>{{inRemark}}</td>
	<td>{{#inTime}}{{/inTime}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td> 
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue inrecord-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/inrecordList}}
</script>