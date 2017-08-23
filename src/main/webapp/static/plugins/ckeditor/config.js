/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn'; //配置语言

	// config.uiColor = '#fff'; // 背景颜色

	// config.width = '800px'; // 宽度

	// config.height = '300px'; // 高度

	// config.skin = 'office2003';// 界面v2,kama,office2003

	config.toolbarCanCollapse = true;// 工具栏是否可以被收缩

	// config.toolbarLocation = 'top';// 工具栏的位置 可选：bottom

	// config.toolbarStartupExpanded = true;// 工具栏默认是否展开

	config.toolbar = "Full";// 工具栏风格Full,Basic

	// 其中("-") 为空间栏的水平分割，("/") 为换行。删除,'Save'
	config.toolbar_Full = [
		{ name: 'document',    items : [ 'Source','-','NewPage','DocProps','Preview','Print','-','Templates' ] },
		{ name: 'clipboard',   items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		{ name: 'editing',     items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
		{ name: 'forms',       items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
		/*'/',*/
		{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'paragraph',   items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		{ name: 'links',       items : [ 'Link','Unlink','Anchor' ] },
		{ name: 'insert',      items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ] },
		/*'/',*/
		{ name: 'styles',      items : [ 'Styles','Format','Font','FontSize' ] },
		{ name: 'colors',      items : [ 'TextColor','BGColor' ] },
		{ name: 'tools',       items : [ 'Maximize'] }/*, 'ShowBlocks','-','About' */
	];

	// 自定义样式列表
	config.stylesSet = [
		{
			name: '标题：加粗',
			element: 'div', styles: {
				'float': 'left',
				'font-size': '14px',
				'text-align': 'center',
				'font-weight': 'bold',
				'width': '100%'
			}
		},
		{
			name: '正文',
			element: 'p', styles: {
				'text-align': 'left',
				'color': '#555',
				'font-size': '12px'
			}
		},
		{
			name: '重点:橙色',
			element: 'span', styles: {
				'color': '#FC5F00',
				'font-size': '14px',
				'font-weight': 'bold'
			}
		},
		{
			name: '重点:黑色',
			element: 'span', styles: {
				'color': '#000',
				'font-size': '14px',
				'font-weight': 'bold'
			}
		},
		{
			name: '橙色文字',	
			element: 'span', styles: {
				'color': '#FC5F00'
			}
		},
		{
			name: '底色标记:黄色',	
			element: 'span', styles: {
				'background-color': 'Yellow'
			}
		},
		{
			name: '删除文字',element: 'del'
		},
		{
			name: '下划线',element: 'ins'
		},
		/* Object Styles */
		{
			name: '图片样式(左)',
			element: 'img',
			attributes: {
				'class': 'left'
			}
		},
		{
			name: '图片样式(右)',
			element: 'img',
			attributes: {
				'class': 'right'
			}
		},
		{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
			name: '无框表格',
			element: 'table',
			attributes: {
				cellpadding: '0',
				cellspacing: '0',
				border: '0',
				width: '394px'
			},styles: {
				'text-align': 'left'
			}
		},
		{
			name: '项目符号列表',
			element: 'li',
			styles: {
				'list-style': 'none',
				'background': 'url(../img/i_case_li.gif) no-repeat scroll left center transparent',
				'line-height': '26px',
				'padding-left': '12px',
				'text-align': 'left',
				'width': '190px'
			}
		}
	];

	config.smiley_path = CKEDITOR.basePath + 'plugins/smiley/images/';// 表情图片路径

	config.smiley_columns = 13;// 表情显示列数

	config.smiley_descriptions = [
		
	];// 表情文字描述

	config.smiley_images = [
		'1.gif', '2.gif', '3.gif', '4.gif', '5.gif', '6.gif', '7.gif', '8.gif', '9.gif', '10.gif', 
		'11.gif', '12.gif', '13.gif', '14.gif', '15.gif', '16.gif', '17.gif', '18.gif', '19.gif', '20.gif', 
		'21.gif', '22.gif', '23.gif', '24.gif', '25.gif', '26.gif', '27.gif', '28.gif', '29.gif', '30.gif', 
		'31.gif', '32.gif', '33.gif', '34.gif', '35.gif', '36.gif', '37.gif', '38.gif', '39.gif', '40.gif', 
		'41.gif', '42.gif', '43.gif', '44.gif', '45.gif', '46.gif', '47.gif', '48.gif', '49.gif', '50.gif', 
		'51.gif', '52.gif', '53.gif', '54.gif', '55.gif', '56.gif', '57.gif', '58.gif', '59.gif', '50.gif', 
		'61.gif', '62.gif', '63.gif', '64.gif', '65.gif', '66.gif', '67.gif', '68.gif', '69.gif', '70.gif', 
		'71.gif', '72.gif', '73.gif', '74.gif', '75.gif'
	];// 表情图片

	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;

	config.tabSpaces = 8;// 8=两个汉字宽度，当用户键入TAB时，编辑器走过的空格数，(&nbsp;)

	config.startupFocus = false;// 页面加载时获取焦点

	config.image_previewText = " ";

	// 在 CKEditor 中集成 CKFinder文件上传功能的配置。
	config.filebrowserBrowseUrl = '../plugins/ckfinder/ckfinder.html';
	config.filebrowserImageBrowseUrl = '../plugins/ckfinder/ckfinder.html?type=Images';
	config.filebrowserFlashBrowseUrl = '../plugins/ckfinder/ckfinder.html?type=Flash';
	config.filebrowserUploadUrl = '../plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	config.filebrowserImageUploadUrl = '../plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	config.filebrowserFlashUploadUrl = '../plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	config.filebrowserWindowWidth = '960';
	config.filebrowserWindowHeight = '700';
	// 文件上传功能的配置结束

};
