package com.serenity.api.serenity.utils;

public class EmailUtil {
    public static final String TEMPLATE_CONVITE = """
            <!DOCTYPE html>
                                <html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" lang="en">
                                <head>
                                	<title></title>
                                	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                                	<meta name="viewport" content="width=device-width, initial-scale=1.0"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!--><!--<![endif]-->
                                	<style>
                                		* {
                                			box-sizing: border-box;
                                		}
                               \s
                                		body {
                                			margin: 0;
                                			padding: 0;
                                		}
                               \s
                                		a[x-apple-data-detectors] {
                                			color: inherit !important;
                                			text-decoration: inherit !important;
                                		}
                               \s
                                		#MessageViewBody a {
                                			color: inherit;
                                			text-decoration: none;
                                		}
                               \s
                                		p {
                                			line-height: inherit
                                		}
                               \s
                                		.desktop_hide,
                                		.desktop_hide table {
                                			mso-hide: all;
                                			display: none;
                                			max-height: 0px;
                                			overflow: hidden;
                                		}
                               \s
                                		.image_block img+div {
                                			display: none;
                                		}
                               \s
                                		sup,
                                		sub {
                                			font-size: 75%%;
                                			line-height: 0;
                                		}
                               \s
                                		#converted-body .list_block ul,
                                		#converted-body .list_block ol,
                                		.body [class~="x_list_block"] ul,
                                		.body [class~="x_list_block"] ol,
                                		u+.body .list_block ul,
                                		u+.body .list_block ol {
                                			padding-left: 20px;
                                		}
                               \s
                                		@media (max-width:520px) {
                                			.desktop_hide table.icons-outer {
                                				display: inline-table !important;
                                			}
                               \s
                                			.mobile_hide {
                                				display: none;
                                			}
                               \s
                                			.row-content {
                                				width: 100%% !important;
                                			}
                               \s
                                			.stack .column {
                                				width: 100%%;
                                				display: block;
                                			}
                               \s
                                			.mobile_hide {
                                				min-height: 0;
                                				max-height: 0;
                                				max-width: 0;
                                				overflow: hidden;
                                				font-size: 0px;
                                			}
                               \s
                                			.desktop_hide,
                                			.desktop_hide table {
                                				display: table !important;
                                				max-height: none !important;
                                			}
                                		}
                                	</style><!--[if mso ]><style>sup, sub { font-size: 100%% !important; } sup { mso-text-raise:10%% } sub { mso-text-raise:-10%% }</style> <![endif]-->
                                </head>
                               \s
                                <body class="body" style="background-color: #FFFFFF; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;">
                                	<table class="nl-container" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF;">
                                		<tbody>
                                			<tr>
                                				<td>
                                					<table class="row row-1" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="100%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="icons_block block-1" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; text-align: center; line-height: 0;">
                                														<tr>
                                															<td class="pad" style="vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;">
                                																<table class="icons-outer" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-table;">
                                																	<tr>
                                																		<td style="vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;"><img class="icon" src="https://d15k2d11r6t6rl.cloudfront.net/pub/bfra/r3lss8lq/nva/kc4/gsd/logo-sy.png" height="auto" width="88" align="center" style="display: block; height: auto; margin: 0 auto; border: 0;"></td>
                                																	</tr>
                                																</table>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                					<table class="row row-2" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #182f4e; border-radius: 0; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="100%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="heading_block block-1" width="100%%" border="0" cellpadding="20" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                														<tr>
                                															<td class="pad">
                                																<h1 style="margin: 0; color: #ffffff; direction: ltr; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 33px; font-weight: 700; letter-spacing: normal; line-height: 120%%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 39.6px;"><span class="tinyMce-placeholder" style="word-break: break-word;">Você foi convidado para o evento %s<br></span></h1>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                					<table class="row row-3" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="100%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-radius: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="paragraph_block block-1" width="100%%" border="0" cellpadding="20" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
                                														<tr>
                                															<td class="pad">
                                																<div style="color:#182f4e;direction:ltr;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:18px;font-weight:400;letter-spacing:0px;line-height:120%%;text-align:left;mso-line-height-alt:21.599999999999998px;">
                                																	<p style="margin: 0;">Olá, %s, você foi convidado para %s na função %s.</p>
                                																</div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                					<table class="row row-4" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="100%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="list_block block-1" width="100%%" border="0" cellpadding="20" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word; color: #182f4e; direction: ltr; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; font-weight: 400; letter-spacing: 0px; line-height: 120%%; text-align: left; mso-line-height-alt: 19.2px;">
                                														<tr>
                                															<td class="pad">
                                																<div style="margin-left:-20px">
                                																	<ul style="margin-top: 0; margin-bottom: 0; list-style-type: revert;">
                                																		<li style="Margin: 0 0 0 0;">Evento:</li>
                                																		<li style="Margin: 0 0 0 0;">Função:</li>
                                																		<li style="Margin: 0 0 0 0;">Local:</li>
                                																		<li style="Margin: 0 0 0 0;">Endereço:</li>
                                																		<li style="Margin: 0 0 0 0;">Entrada:</li>
                                																		<li style="Margin: 0 0 0 0;">Saída prevista:</li>
                                																	</ul>
                                																</div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                					<table class="row row-5" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="25%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="empty_block block-1" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                														<tr>
                                															<td class="pad">
                                																<div></div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                												<td class="column column-2" width="25%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="button_block block-1" width="100%%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                														<tr>
                                															<td class="pad">
                                																<div class="alignment" align="center"><!--[if mso]>
                                <v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word"   style="height:42px;width:99px;v-text-anchor:middle;" arcsize="10%%" fillcolor="#182f4e">
                                <v:stroke dashstyle="Solid" weight="0px" color="#182f4e"/>
                                <w:anchorlock/>
                                <v:textbox inset="0px,0px,0px,0px">
                                <center dir="false" style="color:#ffffff;font-family:sans-serif;font-size:16px">
                                <![endif]-->
                                																	<div style="background-color:#182f4e;border-bottom:0px solid transparent;border-left:0px solid transparent;border-radius:4px;border-right:0px solid transparent;border-top:0px solid transparent;color:#ffffff;display:inline-block;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:16px;font-weight:400;mso-border-alt:none;padding-bottom:5px;padding-top:5px;text-align:center;text-decoration:none;width:auto;word-break:keep-all;"><span style="word-break: break-word; padding-left: 20px; padding-right: 20px; font-size: 16px; display: inline-block; letter-spacing: normal;"><span style="word-break: break-word; line-height: 32px;">Recusar</span></span></div><!--[if mso]></center></v:textbox></v:roundrect><![endif]-->
                                																</div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                												<td class="column column-3" width="25%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="button_block block-1" width="100%%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                														<tr>
                                															<td class="pad">
                                																<div class="alignment" align="center"><!--[if mso]>
                                <v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word"   style="height:42px;width:89px;v-text-anchor:middle;" arcsize="10%%" fillcolor="#ef6c00">
                                <v:stroke dashstyle="Solid" weight="0px" color="#ef6c00"/>
                                <w:anchorlock/>
                                <v:textbox inset="0px,0px,0px,0px">
                                <center dir="false" style="color:#ffffff;font-family:sans-serif;font-size:16px">
                                <![endif]-->
                                																	<div style="background-color:#ef6c00;border-bottom:0px solid transparent;border-left:0px solid transparent;border-radius:4px;border-right:0px solid transparent;border-top:0px solid transparent;color:#ffffff;display:inline-block;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:16px;font-weight:400;mso-border-alt:none;padding-bottom:5px;padding-top:5px;text-align:center;text-decoration:none;width:auto;word-break:keep-all;"><span style="word-break: break-word; padding-left: 20px; padding-right: 20px; font-size: 16px; display: inline-block; letter-spacing: normal;"><span style="word-break: break-word; line-height: 32px;">Aceitar</span></span></div><!--[if mso]></center></v:textbox></v:roundrect><![endif]-->
                                																</div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                												<td class="column column-4" width="25%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="empty_block block-1" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                														<tr>
                                															<td class="pad">
                                																<div></div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                					<table class="row row-6" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="100%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<div class="spacer_block block-1" style="height:60px;line-height:60px;font-size:1px;">&#8202;</div>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                					<table class="row row-7" align="center" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                						<tbody>
                                							<tr>
                                								<td>
                                									<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 500px; margin: 0 auto;" width="500">
                                										<tbody>
                                											<tr>
                                												<td class="column column-1" width="100%%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
                                													<table class="image_block block-1" width="100%%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
                                														<tr>
                                															<td class="pad" style="width:100%%;padding-right:0px;padding-left:0px;">
                                																<div class="alignment" align="center" style="line-height:10px">
                                																	<div style="max-width: 150px;"><img src="https://d15k2d11r6t6rl.cloudfront.net/pub/bfra/r3lss8lq/nva/kc4/gsd/logo-sy.png" style="display: block; height: auto; border: 0; width: 100%%;" width="150" alt title height="auto"></div>
                                																</div>
                                															</td>
                                														</tr>
                                													</table>
                                												</td>
                                											</tr>
                                										</tbody>
                                									</table>
                                								</td>
                                							</tr>
                                						</tbody>
                                					</table>
                                				</td>
                                			</tr>
                                		</tbody>
                                	</table><!-- End -->
                                </body>
                    
                    </html>""";
}
