<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="materialListTemplate" type="x-tmpl-mustache">
{{#materialList}}
 <tr role="row" class="material-name odd" data-id="{{id}}"><!--even -->
	<td>{{materialId}}</td>
	<td>{{materialMaterialname}}</td>
	<td>{{materialMaterialsource}}</td>
	<td>{{materialTargetweight}}</td>
	<td>{{materialRealweight}}</td>
	<td>{{materialImgid}}</td>
	<td>{{materialType}}</td>
	<td>{{materialIrontype}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td> 
	<td>{{materialRemark}}</td>
</tr>
{{/materialList}}
</script>