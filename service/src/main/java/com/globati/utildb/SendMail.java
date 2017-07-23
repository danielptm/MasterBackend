package com.globati.utildb;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.globati.config.Paths;
import com.globati.dbmodel.Deal;
import com.globati.utildb.HelpObjects.Email;
import com.globati.dbmodel.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by daniel on 12/22/16.
 *
 * image lines 627, 1599
 *
 * us this website to convert large html bits of text to strings http://pojo.sodhanalibrary.com/string.html
 */
public class SendMail {

    static final String FROM = "noreply@globati.com";  // Replace with your "From" address. This address must be verified.
    static final String TO = "daniel@globati.com"; // Replace with a "To" address. If your account is still in the
    private static final String key = "AKIAJSYT5343PVMDHCRQ";
    private static final String password = "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM";
    // sandbox, this address must be verified.


    private static final Logger log = LogManager.getLogger(SendMail.class);


    public static boolean sendGuestMail(Email mail) throws Exception {

        String[] emails = new String[10];

        for(int i=0 ; i< mail.getEmails().size(); i++){
            emails[i] = mail.getEmails().get(i);
        }

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        System.out.println(mail.getEmployee().get_image());

        // Create the subject and body of the message.
        Content subject = new Content().withData("My globati ~"+mail.getEmployee().get_firstName());
        Content textBody = new Content().withData(

                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"+
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
                        ""+
                        "<head>"+
                        "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"+
                        "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                        "	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">"+
                        "	<meta name=\"format-detection\" content=\"telephone=no\" />"+
                        "	<!-- disable auto telephone linking in iOS -->"+
                        "	<link href=\"https://fonts.googleapis.com/css?family=Lora|Ubuntu\" rel=\"stylesheet\">"+
                        "	<title>Welcome to Globati!</title>"+
                        "	<style type=\"text/css\">"+
                        "		/* RESET STYLES */"+
                        "		"+
                        "		* {"+
                        "			border-: 1px solid;"+
                        "		}"+
                        "		"+
                        "		html {"+
                        "			background-color: #E1E1E1;"+
                        "			margin: 0;"+
                        "			padding: 0;"+
                        "		}"+
                        "		"+
                        "		body,"+
                        "		#bodyTable,"+
                        "		#bodyCell,"+
                        "		#bodyCell {"+
                        "			height: 100% !important;"+
                        "			margin: 0;"+
                        "			padding: 0;"+
                        "			width: 100% !important;"+
                        "			font-family: Ubuntu, Helvetica, Arial, \"Lucida Grande\", sans-serif;"+
                        "		}"+
                        "		"+
                        "		table {"+
                        "			border-collapse: collapse;"+
                        "		}"+
                        "		"+
                        "		table[id=bodyTable] {"+
                        "			width: 100%!important;"+
                        "			margin: auto;"+
                        "			max-width: 500px!important;"+
                        "			color: #7A7A7A;"+
                        "			font-weight: normal;"+
                        "		}"+
                        "		"+
                        "		img,"+
                        "		a img {"+
                        "			border: 0;"+
                        "			outline: none;"+
                        "			text-decoration: none;"+
                        "			height: auto;"+
                        "			line-height: 100%;"+
                        "		}"+
                        "		"+
                        "		a {"+
                        "			text-decoration: none !important;"+
                        "			border-bottom: 1px solid;"+
                        "		}"+
                        "		"+
                        "		h1,"+
                        "		h2,"+
                        "		h3,"+
                        "		h4,"+
                        "		h5,"+
                        "		h6 {"+
                        "			color: #323333;"+
                        "			font-weight: normal;"+
                        "			font-family: Helvetica;"+
                        "			font-size: 20px;"+
                        "			line-height: 125%;"+
                        "			text-align: Left;"+
                        "			letter-spacing: normal;"+
                        "			margin-top: 0;"+
                        "			margin-right: 0;"+
                        "			margin-bottom: 10px;"+
                        "			margin-left: 0;"+
                        "			padding-top: 0;"+
                        "			padding-bottom: 0;"+
                        "			padding-left: 0;"+
                        "			padding-right: 0;"+
                        "		}"+
                        "		/* CLIENT-SPECIFIC STYLES */"+
                        "		"+
                        "		.ReadMsgBody {"+
                        "			width: 100%;"+
                        "		}"+
                        "		"+
                        "		.ExternalClass {"+
                        "			width: 100%;"+
                        "		}"+
                        "		/* Force Hotmail/Outlook.com to display emails at full width. */"+
                        "		"+
                        "		.ExternalClass,"+
                        "		.ExternalClass p,"+
                        "		.ExternalClass span,"+
                        "		.ExternalClass font,"+
                        "		.ExternalClass td,"+
                        "		.ExternalClass div {"+
                        "			line-height: 100%;"+
                        "		}"+
                        "		/* Force Hotmail/Outlook.com to display line heights normally. */"+
                        "		"+
                        "		table,"+
                        "		td {"+
                        "			mso-table-lspace: 0pt;"+
                        "			mso-table-rspace: 0pt;"+
                        "		}"+
                        "		/* Remove spacing between tables in Outlook 2007 and up. */"+
                        "		"+
                        "		#outlook a {"+
                        "			padding: 0;"+
                        "		}"+
                        "		/* Force Outlook 2007 and up to provide a \"view in browser\" message. */"+
                        "		"+
                        "		img {"+
                        "			-ms-interpolation-mode: bicubic;"+
                        "			display: block;"+
                        "			outline: none;"+
                        "			text-decoration: none;"+
                        "		}"+
                        "		/* Force IE to smoothly render resized images. */"+
                        "		"+
                        "		body,"+
                        "		table,"+
                        "		td,"+
                        "		p,"+
                        "		a,"+
                        "		li,"+
                        "		blockquote {"+
                        "			-ms-text-size-adjust: 100%;"+
                        "			-webkit-text-size-adjust: 100%;"+
                        "			font-weight: normal!important;"+
                        "		}"+
                        "		/* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */"+
                        "		"+
                        "		.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {"+
                        "			padding-top: 10px !important;"+
                        "		}"+
                        "		/* Force hotmail to push 2-grid sub headers down */"+
                        "		/* ========== Page Styles ========== */"+
                        "		"+
                        "		h1 {"+
                        "			display: block;"+
                        "			font-size: 26px;"+
                        "			font-style: normal;"+
                        "			font-weight: normal;"+
                        "			line-height: 100%;"+
                        "		}"+
                        "		"+
                        "		h2 {"+
                        "			display: block;"+
                        "			font-size: 20px;"+
                        "			font-style: normal;"+
                        "			font-weight: normal;"+
                        "			line-height: 120%;"+
                        "		}"+
                        "		"+
                        "		h3 {"+
                        "			display: block;"+
                        "			font-size: 17px;"+
                        "			font-style: normal;"+
                        "			font-weight: normal;"+
                        "			line-height: 110%;"+
                        "		}"+
                        "		"+
                        "		h4 {"+
                        "			display: block;"+
                        "			font-size: 18px;"+
                        "			font-style: italic;"+
                        "			font-weight: normal;"+
                        "			line-height: 100%;"+
                        "		}"+
                        "		"+
                        "		.flexibleImage {"+
                        "			height: auto;"+
                        "		}"+
                        "		"+
                        "		.linkRemoveBorder {"+
                        "			border-bottom: 0 !important;"+
                        "		}"+
                        "		"+
                        "		table[class=flexibleContainerCellDivider] {"+
                        "			padding-bottom: 0 !important;"+
                        "			padding-top: 0 !important;"+
                        "		}"+
                        "		"+
                        "		body,"+
                        "		#bodyTable {"+
                        "			background-color: #E1E1E1;"+
                        "		}"+
                        "		"+
                        "		#emailHeader {"+
                        "			background-color: #E1E1E1;"+
                        "		}"+
                        "		"+
                        "		#emailBody {"+
                        "			background-color: #FFFFFF;"+
                        "		}"+
                        "		"+
                        "		#emailFooter {"+
                        "			background-color: #E1E1E1;"+
                        "		}"+
                        "		"+
                        "		.nestedContainer {"+
                        "			background-color: #F8F8F8;"+
                        "			border: 1px solid #CCCCCC;"+
                        "		}"+
                        "		"+
                        "		.emailButton {"+
                        "			background-color: #205478;"+
                        "			border-collapse: separate;"+
                        "		}"+
                        "		"+
                        "		.buttonContent {"+
                        "			color: #FFFFFF;"+
                        "			font-family: Helvetica;"+
                        "			font-size: 18px;"+
                        "			font-weight: bold;"+
                        "			line-height: 100%;"+
                        "			padding: 15px;"+
                        "			text-align: center;"+
                        "		}"+
                        "		"+
                        "		.buttonContent a {"+
                        "			color: #FFFFFF;"+
                        "			display: block;"+
                        "			text-decoration: none!important;"+
                        "			border: 0!important;"+
                        "		}"+
                        "		"+
                        "		.emailCalendar {"+
                        "			background-color: #FFFFFF;"+
                        "			border: 1px solid #CCCCCC;"+
                        "		}"+
                        "		"+
                        "		.emailCalendarMonth {"+
                        "			background-color: #205478;"+
                        "			color: #FFFFFF;"+
                        "			font-family: Ubuntu, Helvetica, Arial, sans-serif;"+
                        "			font-size: 16px;"+
                        "			font-weight: bold;"+
                        "			padding-top: 10px;"+
                        "			padding-bottom: 10px;"+
                        "			text-align: center;"+
                        "		}"+
                        "		"+
                        "		.emailCalendarDay {"+
                        "			color: #205478;"+
                        "			font-family: Ubuntu, Helvetica, Arial, sans-serif;"+
                        "			font-size: 60px;"+
                        "			font-weight: bold;"+
                        "			line-height: 100%;"+
                        "			padding-top: 20px;"+
                        "			padding-bottom: 20px;"+
                        "			text-align: center;"+
                        "		}"+
                        "		"+
                        "		.imageContentText {"+
                        "			margin-top: 10px;"+
                        "			line-height: 0;"+
                        "		}"+
                        "		"+
                        "		.imageContentText a {"+
                        "			line-height: 0;"+
                        "		}"+
                        "		"+
                        "		#invisibleIntroduction {"+
                        "			display: none !important;"+
                        "		}"+
                        "		/* Removing the introduction text from the view */"+
                        "		/*FRAMEWORK HACKS & OVERRIDES */"+
                        "		"+
                        "		span[class=ios-color-hack] a {"+
                        "			color: #275100!important;"+
                        "			text-decoration: none!important;"+
                        "		}"+
                        "		/* Remove all link colors in IOS (below are duplicates based on the color preference) */"+
                        "		"+
                        "		span[class=ios-color-hack2] a {"+
                        "			color: #205478!important;"+
                        "			text-decoration: none!important;"+
                        "		}"+
                        "		"+
                        "		span[class=ios-color-hack3] a {"+
                        "			color: #8B8B8B!important;"+
                        "			text-decoration: none!important;"+
                        "		}"+
                        "		/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled."+
                        "			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/."+
                        "			*/"+
                        "		"+
                        "		.a[href^=\"tel\"],"+
                        "		a[href^=\"sms\"] {"+
                        "			text-decoration: none!important;"+
                        "			color: #606060!important;"+
                        "			pointer-events: none!important;"+
                        "			cursor: default!important;"+
                        "		}"+
                        "		"+
                        "		.mobile_link a[href^=\"tel\"],"+
                        "		.mobile_link a[href^=\"sms\"] {"+
                        "			text-decoration: none!important;"+
                        "			color: #606060!important;"+
                        "			pointer-events: auto!important;"+
                        "			cursor: default!important;"+
                        "		}"+
                        "		/* MOBILE STYLES */"+
                        "		"+
                        "		@media only screen and (max-width: 480px) {"+
                        "			/*////// CLIENT-SPECIFIC STYLES //////*/"+
                        "			body {"+
                        "				width: 100% !important;"+
                        "				min-width: 100% !important;"+
                        "			}"+
                        "			/* Force iOS Mail to render the email at full width. */"+
                        "			/* FRAMEWORK STYLES */"+
                        "			/*"+
                        "				CSS selectors are written in attribute"+
                        "				selector format to prevent Yahoo Mail"+
                        "				from rendering media query styles on"+
                        "				desktop."+
                        "				*/"+
                        "			/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/"+
                        "			table[id=\"emailHeader\"],"+
                        "			table[id=\"emailBody\"],"+
                        "			table[id=\"emailFooter\"],"+
                        "			table[class=\"flexibleContainer\"],"+
                        "			td[class=\"flexibleContainerCell\"] {"+
                        "				width: 100% !important;"+
                        "			}"+
                        "			td[class=\"flexibleContainerBox\"],"+
                        "			td[class=\"flexibleContainerBox\"] table {"+
                        "				display: block;"+
                        "				width: 100%;"+
                        "				text-align: left;"+
                        "			}"+
                        "			/*"+
                        "				The following style rule makes any"+
                        "				image classed with 'flexibleImage'"+
                        "				fluid when the query activates."+
                        "				Make sure you add an inline max-width"+
                        "				to those images to prevent them"+
                        "				from blowing out."+
                        "				*/"+
                        "			td[class=\"imageContent\"] img {"+
                        "				height: auto !important;"+
                        "				width: 100% !important;"+
                        "				max-width: 100% !important;"+
                        "			}"+
                        "			img[class=\"flexibleImage\"] {"+
                        "				height: auto !important;"+
                        "				width: 100% !important;"+
                        "				max-width: 100% !important;"+
                        "			}"+
                        "			img[class=\"flexibleImageSmall\"] {"+
                        "				height: auto !important;"+
                        "				width: auto !important;"+
                        "			}"+
                        "			/*"+
                        "				Create top space for every second element in a block"+
                        "				*/"+
                        "			table[class=\"flexibleContainerBoxNext\"] {"+
                        "				padding-top: 10px !important;"+
                        "			}"+
                        "			/*"+
                        "				Make buttons in the email span the"+
                        "				full width of their container, allowing"+
                        "				for left- or right-handed ease of use."+
                        "				*/"+
                        "			table[class=\"emailButton\"] {"+
                        "				width: 100% !important;"+
                        "			}"+
                        "			td[class=\"buttonContent\"] {"+
                        "				padding: 0 !important;"+
                        "			}"+
                        "			td[class=\"buttonContent\"] a {"+
                        "				padding: 15px !important;"+
                        "			}"+
                        "		}"+
                        "		/*  CONDITIONS FOR ANDROID DEVICES ONLY"+
                        "			*   http://developer.android.com/guide/webapps/targeting.html"+
                        "			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;"+
                        "			=====================================================*/"+
                        "		"+
                        "		@media only screen and (-webkit-device-pixel-ratio:.75) {"+
                        "			/* Put CSS for low density (ldpi) Android layouts in here */"+
                        "		}"+
                        "		"+
                        "		@media only screen and (-webkit-device-pixel-ratio:1) {"+
                        "			/* Put CSS for medium density (mdpi) Android layouts in here */"+
                        "		}"+
                        "		"+
                        "		@media only screen and (-webkit-device-pixel-ratio:1.5) {"+
                        "			/* Put CSS for high density (hdpi) Android layouts in here */"+
                        "		}"+
                        "		/* end Android targeting */"+
                        "		/* CONDITIONS FOR IOS DEVICES ONLY"+
                        "			=====================================================*/"+
                        "		"+
                        "		@media only screen and (min-device-width: 320px) and (max-device-width:568px) {}"+
                        "		/* end IOS targeting */"+
                        "	</style>"+
                        "	<!--"+
                        "			Outlook Conditional CSS"+
                        ""+
                        "			These two style blocks target Outlook 2007 & 2010 specifically, forcing"+
                        "			columns into a single vertical stack as on mobile clients. This is"+
                        "			primarily done to avoid the 'page break bug' and is optional."+
                        ""+
                        "			More information here:"+
                        "			http://templates.mailchimp.com/development/css/outlook-conditional-css"+
                        "		-->"+
                        "	<!--[if mso 12]>"+
                        "			<style type=\"text/css\">"+
                        "				.flexibleContainer{display:block !important; width:100% !important;}"+
                        "			</style>"+
                        "		<![endif]-->"+
                        "	<!--[if mso 14]>"+
                        "			<style type=\"text/css\">"+
                        "				.flexibleContainer{display:block !important; width:100% !important;}"+
                        "			</style>"+
                        "		<![endif]-->"+
                        "</head>"+
                        ""+
                        "<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">"+
                        "	<!-- CENTER THE EMAIL // -->"+
                        "	<!--"+
                        "		1.  The center tag should normally put all the"+
                        "			content in the middle of the email page."+
                        "			I added \"table-layout: fixed;\" style to force"+
                        "			yahoomail which by default put the content left."+
                        ""+
                        "		2.  For hotmail and yahoomail, the contents of"+
                        "			the email starts from this center, so we try to"+
                        "			apply necessary styling e.g. background-color."+
                        "		-->"+
                        "	<center style=\"background-color:#E1E1E1;\">"+
                        "		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">"+
                        "			<tr>"+
                        "				<td align=\"center\" valign=\"top\" id=\"bodyCell\">"+
                        "					<!-- EMAIL HEADER // -->"+
                        "					<!--"+
                        "							The table \"emailBody\" is the email's container."+
                        "							Its width can be set to 100% for a color band"+
                        "							that spans the width of the page."+
                        "						-->"+
                        "					<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailHeader\">"+
                        "						<!-- HEADER ROW // -->"+
                        "						<tr>"+
                        "							<td align=\"center\" valign=\"top\">"+
                        "								<!-- CENTERING TABLE // -->"+
                        "								<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "									<tr>"+
                        "										<td align=\"center\" valign=\"top\">"+
                        "											<!-- FLEXIBLE CONTAINER // -->"+
                        "											<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                        "												<tr>"+
                        "													<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                        "														<!-- CONTENT TABLE // -->"+
                        "														<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "															<tr>"+
                        "																<!--"+
                        "																		The \"invisibleIntroduction\" is the text used for short preview"+
                        "																		of the email before the user opens it (50 characters max). Sometimes,"+
                        "																		you do not want to show this message depending on your design but this"+
                        "																		text is highly recommended."+
                        ""+
                        "																		You do not have to worry if it is hidden, the next <td> will automatically"+
                        "																		center and apply to the width 100% and also shrink to 50% if the first <td>"+
                        "																		is visible."+
                        "																	-->"+
                        "																<td align=\"left\" valign=\"middle\" id=\"invisibleIntroduction\" class=\"flexibleContainerBox\" style=\"display:none !important; mso-hide:all;\">"+
                        "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">"+
                        "																		<tr>"+
                        "																			<td align=\"left\" class=\"textContent\">"+
                        "																				<div style=\"font-family:Ubuntu,Helvetica,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\"> Globati - you've been invited to be part of it!</div>"+
                        "																			</td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																</td>"+
                        "																<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">"+
                        "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">"+
                        "																		<tr>"+
                        "																			<td align=\"left\" class=\"textContent\">"+
                        "																				<!-- CONTENT // -->"+
                        "																				<div style=\"font-family:Ubuntu,Helvetica,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\"> If you can't see this message, <a href=\"#\" target=\"_blank\" style=\"text-decoration:none;border-bottom:1px solid #828282;color:#828282;\"><span style=\"color:#828282;\">view it in your browser</span></a>. </div>"+
                        "																			</td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																</td>"+
                        "															</tr>"+
                        "														</table>"+
                        "													</td>"+
                        "												</tr>"+
                        "											</table>"+
                        "											<!-- // FLEXIBLE CONTAINER -->"+
                        "										</td>"+
                        "									</tr>"+
                        "								</table>"+
                        "								<!-- // CENTERING TABLE -->"+
                        "							</td>"+
                        "						</tr>"+
                        "						<!-- // END -->"+
                        "					</table>"+
                        "					<!-- // END -->"+
                        "					<!-- EMAIL BODY // -->"+
                        "					<!--"+
                        "							The table \"emailBody\" is the email's container."+
                        "							Its width can be set to 100% for a color band"+
                        "							that spans the width of the page."+
                        "						-->"+
                        "					<table bgcolor=\"#FFFFFF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">"+
                        "						<!-- MODULE ROW // -->"+
                        "						<tr>"+
                        "							<td align=\"center\" valign=\"top\">"+
                        "								<!-- CENTERING TABLE // -->"+
                        "								<table bgcolor=\"#FFFFFF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"160px\">"+
                        "									<tr>"+
                        "										<td align=\"center\" valign=\"top\">"+
                        "											<!-- FLEXIBLE CONTAINER // -->"+
                        "											<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                        "												<tr>"+
                        "													<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                        "														<!-- CONTENT TABLE // -->"+
                        "														<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "															<tr>"+
                        "																<td align=\"right\" class=\"textContent\" width=\"41%\"> <img src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/globati.png\" width=\"100\" class=\"flexibleImageSmall\" style=\"max-width:100%;\" alt=\"\" title=\"\" /> </td>"+
                        "																<td align=\"left\" width=\"69%\" style=\"color:#323333;line-height:125%;font-family:Ubuntu,Helvetica,sans-serif;font-size:50px;font-weight:700;margin-top:0;margin-bottom:3px;text-align:left;\"><b>Globati</b> </td>"+
                        "															</tr>"+
                        "														</table>"+
                        "														<!-- // CONTENT TABLE -->"+
                        "													</td>"+
                        "												</tr>"+
                        "											</table>"+
                        "											<!-- // FLEXIBLE CONTAINER -->"+
                        "										</td>"+
                        "									</tr>"+
                        "								</table>"+
                        "								<!-- // CENTERING TABLE -->"+
                        "							</td>"+
                        "						</tr>"+
                        "						<!-- // MODULE ROW -->"+
                        "						<!-- MODULE ROW // -->"+
                        "						<tr>"+
                        "							<td align=\"center\" valign=\"top\">"+
                        "								<!-- CENTERING TABLE // -->"+
                        "								<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "									<tr>"+
                        "										<td align=\"center\" valign=\"top\" bgcolor=\"\">"+
                        "											<!-- FLEXIBLE CONTAINER // -->"+
                        "											<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                        "												<tr>"+
                        "													<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\" bgcolor=\"#1E90FF\">"+
                        "														<!-- CONTENT TABLE // -->"+
                        "														<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "															<tr>"+
                        "																<td align=\"left\" valign=\"top\" class=\"flexibleContainerBox\">"+
                        "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90\" style=\"max-width:100%;\">"+
                        "																		<tr>"+
                        "																			<td align=\"left\" class=\"textContent\"> <img src=\""+mail.getEmployee().get_image()+"\" width=\"100\" class=\"flexibleImageSmall\" style=\"max-width:100%;\" alt=\"\" title=\"\" /> </td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																</td>"+
                        "																<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">"+
                        "																	<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"350\" style=\"max-width:100%;\">"+
                        "																		<tr>"+
                        "																			<!--<td align=\"left\" class=\"textContent\">"+
                        "																				<h3 style=\"margin-top:0;margin-bottom:3px;padding:10px;\"></h3> </td>-->"+
                        "																			<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">"+
                        "																				<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"350\" style=\"max-width:100%;\">"+
                        "																					<tr>"+
                        "																						<td align=\"left\" class=\"textContent\" style=\"padding-left:30px; padding-right;\">"+
                        "																							<h3 style=\"color:#323333;line-height:125%;font-family:Ubuntu,Helvetica,sans-serif;font-size:29px;font-weight:700;margin-top:0;margin-bottom:3px;text-align:left;\">Hey, I'm "+mail.getEmployee().get_firstName()+"! <a style=\"color:#FFFFFF;\" href=\"#\" target=\"_blank\"></a></h3>"+
                        "																							<div style=\"text-align:left;font-family:Ubuntu,Helvetica,sans-serif;font-size:15px;margin-bottom:0;color:#323333;line-height:135%;\">"+mail.getEmployee().get_welcomeMail()+"</div>"+
                        "																						</td>"+
                        "																					</tr>"+
                        "																				</table>"+
                        "																			</td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																	<!-- // CONTENT TABLE -->"+
                        "																</td>"+
                        "															</tr>"+
                        "														</table>"+
                        "														<!-- // FLEXIBLE CONTAINER -->"+
                        "													</td>"+
                        "												</tr>"+
                        "											</table>"+
                        "											<!-- // CENTERING TABLE -->"+
                        "										</td>"+
                        "									</tr>"+
                        "									<!-- // MODULE ROW -->"+
                        "									<!-- MODULE ROW // -->"+
                        "									<tr>"+
                        "										<td align=\"center\" valign=\"top\">"+
                        "											<!-- CENTERING TABLE // -->"+
                        "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "												<tr>"+
                        "													<td align=\"center\" valign=\"top\">"+
                        "														<!-- FLEXIBLE CONTAINER // -->"+
                        "														<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\" bgcolor=\"#FFFFFF\">"+
                        "															<tr>"+
                        "																<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                        "																	<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">"+
                        "																		<tr>"+
                        "																			<td align=\"center\" valign=\"top\">"+
                        "																				<!-- CONTENT TABLE // -->"+
                        "																				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "																					<tr>"+
                        "																						<td valign=\"top\" class=\"textContent\">"+
                        "																							<!--"+
                        "																						The \"mc:edit\" is a feature for MailChimp which allows"+
                        "																						you to edit a certain row. It makes it easy for you to quickly edit row sections."+
                        "																						http://kb.mailchimp.com/templates/code/create-editable-content-areas-with-mailchimps-template-language"+
                        "																					-->"+
                        "																							<h3 mc:edit=\"header\" style=\"color:#323333;line-height:125%;font-family:Ubuntu,Helvetica,sans-serif;font-size:29px;font-weight:700;margin-top:0;margin-bottom:3px;text-align:left;\">Welcome to Globati!</h3>"+
                        "																							<div mc:edit=\"body\" style=\"text-align:left;font-family:Ubuntu,Helvetica,sans-serif;font-size:15px;margin-bottom:0;color:#323333;line-height:135%;\">Check out "+mail.getEmployee().get_firstName()+"'s profile below, you can also go to globati.com/myglobati to see other locals in the area on the globati network. </div>"+
                        "																						</td>"+
                        "																					</tr>"+
                        "																				</table>"+
                        "																				<!-- // CONTENT TABLE -->"+
                        "																			</td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																</td>"+
                        "															</tr>"+
                        "														</table>"+
                        "														<!-- // FLEXIBLE CONTAINER -->"+
                        "													</td>"+
                        "												</tr>"+
                        "											</table>"+
                        "											<!-- // CENTERING TABLE -->"+
                        "										</td>"+
                        "									</tr>"+
                        "									<!-- // MODULE ROW -->"+
                        "									<!-- MODULE ROW // -->"+
                        "									<tr>"+
                        "										<td align=\"center\" valign=\"top\">"+
                        "											<!-- CENTERING TABLE // -->"+
                        "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#FFFFFF\">"+
                        "												<tr style=\"padding-top:0;\">"+
                        "													<td align=\"center\" valign=\"top\">"+
                        "														<!-- FLEXIBLE CONTAINER // -->"+
                        "														<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                        "															<tr>"+
                        "																<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                        "																	<!-- CONTENT TABLE // -->"+
                        "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: deeppink;\">"+
                        "																		<tr>"+
                        "																			<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\"> <a style=\"color:#FFFFFF;text-decoration:none;font-family:Ubuntu,Helvetica,sans-serif;font-size:21px;line-height:135%;\" href=\"#\" target=\"_blank\"><a href=\""+Paths.getActiveStaticGlobati()+mail.getEmployee().get_globatiUsername()+"\" >"+mail.getEmployee().get_firstName()+"'s globati</a> </td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																	<!-- // CONTENT TABLE -->"+
                        "																</td>"+
                        "															</tr>"+
                        "														</table>"+
                        "														<!-- // FLEXIBLE CONTAINER -->"+
                        "													</td>"+
                        "												</tr>"+
                        "											</table>"+
                        "											<!-- // CENTERING TABLE -->"+
                        "										</td>"+
                        "									</tr>"+
                        "									<!-- // MODULE ROW -->"+
                        "									<!-- MODULE ROW // -->"+
                        "									<tr bgcolor=\"#323333\">"+
                        "										<td align=\"center\" valign=\"top\">"+
                        "											<!-- CENTERING TABLE // -->"+
                        "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "												<tr>"+
                        "													<td align=\"center\" valign=\"top\">"+
                        "														<!-- FLEXIBLE CONTAINER // -->"+
                        "														<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                        "															<tr>"+
                        "																<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                        "																	<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">"+
                        "																		<tr>"+
                        "																			<td align=\"center\" valign=\"top\">"+
                        "																				<!-- CONTENT TABLE // -->"+
                        "																				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                        "																					<tr>"+
                        "																						<td valign=\"top\" class=\"textContent\">"+
                        "																							<div style=\"text-align:center;font-family:Ubuntu,Helvetica,sans-serif;font-size:15px;margin-bottom:0;margin-top:3px;color:#FFFFFF;line-height:135%;\"> © 2017 <a href=\"http://www.globati.com\" target=\"_blank\" style=\"text-decoration:none;color:#FFFFFF;\"><span style=\"color:#FFFFF;\">Globati</span></a>. All rights reserved.</div>"+
                        "																						</td>"+
                        "																					</tr>"+
                        "																				</table>"+
                        "																				<!-- // CONTENT TABLE -->"+
                        "																			</td>"+
                        "																		</tr>"+
                        "																	</table>"+
                        "																</td>"+
                        "															</tr>"+
                        "														</table>"+
                        "														<!-- // FLEXIBLE CONTAINER -->"+
                        "													</td>"+
                        "												</tr>"+
                        "											</table>"+
                        "											<!-- // CENTERING TABLE -->"+
                        "										</td>"+
                        "									</tr>"+
                        "									<!-- // MODULE ROW -->"+
                        "									<!-- // END -->"+
                        "							</td>"+
                        "						</tr>"+
                        "						</table>"+
                        "				</td>"+
                        "			</tr>"+
                        "			</table>"+
                        "	</center>"+
                        "</body>"+
                        ""+
                        "</html>"


        );


        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }
    }

    public static boolean sendReceipt(Deal deal) throws Exception {
        String[] emails = new String[]{deal.get_email()};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Your receipt for advertising on globati");
        Content textBody = new Content().withData(
                "<!DOCTYPE html>"+
                        "<html lang=\"\">"+
                        ""+
                        "<head>"+
                        "    <meta charset=\"utf-8\">"+
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                        "    <link href=\"https://fonts.googleapis.com/css?family=Lora|Ubuntu\" rel=\"stylesheet\">"+
                        "    <title>Globati Receipt</title>"+
                        "</head>"+
                        ""+
                        "<body>"+
                        "    <table style=\"box-sizing: border-box;  border-collapse: collapse; text-align: center; font-family: Ubuntu, sans-serif; border-top: 3px solid; border-right: 3px solid; border-left: 3px solid; border-bottom: 3px solid; table-layout: fixed; width: 1260px; height: 1782px;\">"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; padding: 0 0 0 0; background-color: #DC143C;\" colspan=\"6\"><img src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/Logo_and_Name.png\" width=624px height=272px alt=\"Globati Logo\" /></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; font-weight: 700; padding: 0.25em 0.5em 0.25em 0.5em; background-color: #DC143C; color: ;\" colspan=\"3\">Order Number"+
                        "                <br> "+deal.get_transactionId()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 48px; padding: 2em 0.25em 0em 0.25em; background-color: #DC143C; color: #FFF;\" colspan=\"12\">Thanks for advertising with us!</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.5em 5em 0em 5em; text-align: justify; background-color: #DC143C; color: #FFF;\" colspan=\"12\"></td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 48px; font-weight: 700; padding: 1em 0.25em 1em 0.25em; background-color: #DC143C; color: #FFF;\" colspan=\"12\">I N V O I C E</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; font-weight:700; padding: 1em 0.25em 1em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"12\">Order Summary</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; padding: 0.25em 0.25em 0.25em 0.25em; border-bottom: 2px solid; background-color: #323333; color: #FFF;\" colspan=\"9\">Online advertisement</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; padding: 0 0 0 0; background-color: #323333; color: #FFF;\" rowspan=\"2\" colspan=\"3\"></td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Business Type</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Validity Period</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Cost</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">"+deal.get_dealtype()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">"+deal.get_plan()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">"+deal.get_cost()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\"></td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; font-weight: 700; padding: 1em 0.25em 1em 0.25em;\" colspan=\"12\">Customer Information</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 21px; font-weight: 700; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">Location</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 21px; font-weight: 700; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">Billing Address</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_location()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_location()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_street()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_billingStreet()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_billingRegion()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 1em 0.25em;\" colspan=\"6\">"+deal.get_country()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 1em 0.25em; border-bottom: 1px dashed;\" colspan=\"6\">"+deal.get_country()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.75em 0.25em 0.25em 0.25em;\" colspan=\"6\">You will also get a receipt with the transaction details by our payment partner Braintree</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.75em 0.25em 0.25em 0.25em;\" colspan=\"6\">Contact "+deal.get_employee().get_firstName()+" at "+deal.get_employee().get_email()+" if you have any questions</td>"+
                        "        </tr>"+
                        "        <tr style=\"border-bottom: 3px solid;\">"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 2em 0.25em;\" colspan=\"6\"></td>"+
                        "        </tr>"+
                        "        <tr style=\"background-color: #323333; color: #FFF;\">"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; font-weight: 700; padding: 1em 0.25em 1em 0.25em;\" colspan=\"12\">© 2017 Globati. All rights reserved.</td>"+
                        "        </tr>"+
                        "    </table>"+
                        "</body>"+
                        ""+
                        "</html>"
            );

        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }
    }

//    public static boolean sendReceptionistReport(List<File> files) throws MessagingException {
//        String recipient = "danielptm@me.com";
//        String sender = "noreply.globati@gmail.com";
//        String host = "smtp.gmail.com";
//        final String username = "noreply.globati@gmail.com";//change accordingly
//        final String password = "j23$79@15z";//change accordingly
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            DataSource ds = new FileDataSource(files.get(0));
//            DataSource ds2 = new FileDataSource(files.get(1));
//
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(sender));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(recipient));
//
//            // Set Subject: header field
//            message.setSubject("Receptionists paypal batchpayout");
//
//            // Create the message part
//            BodyPart messageBodyPart = new MimeBodyPart();
//            BodyPart messageBodyPart2 = new MimeBodyPart();
//
//            // Now set the actual message
//            messageBodyPart.setText("These are the files for receptioist recruitment deals");
//
//            // Create a multipar message
//            Multipart multipart = new MimeMultipart();
//
//            // Set text message part
//            multipart.addBodyPart(messageBodyPart);
//
//            // Part two is attachment
//            messageBodyPart = new MimeBodyPart();
////            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(ds));
//            messageBodyPart2.setDataHandler(new DataHandler(ds2));
//            messageBodyPart.setFileName("receptionistWith.csv");
//            messageBodyPart2.setFileName("receptionistWithout.csv");
//            multipart.addBodyPart(messageBodyPart);
//            multipart.addBodyPart(messageBodyPart2);
//
//            // Send the complete message parts
//            message.setContent(multipart);
//
//            // Send message
//            Transport.send(message);
//
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }

    public static boolean sendRecruitmentMail(Employee employee, String email, String businessName) throws Exception {

        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData(employee.get_firstName()+" is inviting "+businessName+" to develop a business partnership on globati");
        Content textBody = new Content().withData(

                    "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"+
                            "<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
                            ""+
                            "<head>"+
                            "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"+
                            "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                            "	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">"+
                            "	<meta name=\"format-detection\" content=\"telephone=no\" />"+
                            "	<!-- disable auto telephone linking in iOS -->"+
                            "	<link href=\"https://fonts.googleapis.com/css?family=Lora|Ubuntu\" rel=\"stylesheet\">"+
                            "	<title>Welcome to Globati!</title>"+
                            "	<style type=\"text/css\">"+
                            "		/* RESET STYLES */"+
                            "		"+
                            "		* {"+
                            "			border-: 1px solid;"+
                            "		}"+
                            "		"+
                            "		html {"+
                            "			background-color: #E1E1E1;"+
                            "			margin: 0;"+
                            "			padding: 0;"+
                            "		}"+
                            "		"+
                            "		body,"+
                            "		#bodyTable,"+
                            "		#bodyCell,"+
                            "		#bodyCell {"+
                            "			height: 100% !important;"+
                            "			margin: 0;"+
                            "			padding: 0;"+
                            "			width: 100% !important;"+
                            "			font-family: Ubuntu, Helvetica, Arial, \"Lucida Grande\", sans-serif;"+
                            "		}"+
                            "		"+
                            "		table {"+
                            "			border-collapse: collapse;"+
                            "		}"+
                            "		"+
                            "		table[id=bodyTable] {"+
                            "			width: 100%!important;"+
                            "			margin: auto;"+
                            "			max-width: 500px!important;"+
                            "			color: #7A7A7A;"+
                            "			font-weight: normal;"+
                            "		}"+
                            "		"+
                            "		img,"+
                            "		a img {"+
                            "			border: 0;"+
                            "			outline: none;"+
                            "			text-decoration: none;"+
                            "			height: auto;"+
                            "			line-height: 100%;"+
                            "		}"+
                            "		"+
                            "		a {"+
                            "			text-decoration: none !important;"+
                            "			border-bottom: 1px solid;"+
                            "		}"+
                            "		"+
                            "		h1,"+
                            "		h2,"+
                            "		h3,"+
                            "		h4,"+
                            "		h5,"+
                            "		h6 {"+
                            "			color: #323333;"+
                            "			font-weight: normal;"+
                            "			font-family: Helvetica;"+
                            "			font-size: 20px;"+
                            "			line-height: 125%;"+
                            "			text-align: Left;"+
                            "			letter-spacing: normal;"+
                            "			margin-top: 0;"+
                            "			margin-right: 0;"+
                            "			margin-bottom: 10px;"+
                            "			margin-left: 0;"+
                            "			padding-top: 0;"+
                            "			padding-bottom: 0;"+
                            "			padding-left: 0;"+
                            "			padding-right: 0;"+
                            "		}"+
                            "		/* CLIENT-SPECIFIC STYLES */"+
                            "		"+
                            "		.ReadMsgBody {"+
                            "			width: 100%;"+
                            "		}"+
                            "		"+
                            "		.ExternalClass {"+
                            "			width: 100%;"+
                            "		}"+
                            "		/* Force Hotmail/Outlook.com to display emails at full width. */"+
                            "		"+
                            "		.ExternalClass,"+
                            "		.ExternalClass p,"+
                            "		.ExternalClass span,"+
                            "		.ExternalClass font,"+
                            "		.ExternalClass td,"+
                            "		.ExternalClass div {"+
                            "			line-height: 100%;"+
                            "		}"+
                            "		/* Force Hotmail/Outlook.com to display line heights normally. */"+
                            "		"+
                            "		table,"+
                            "		td {"+
                            "			mso-table-lspace: 0pt;"+
                            "			mso-table-rspace: 0pt;"+
                            "		}"+
                            "		/* Remove spacing between tables in Outlook 2007 and up. */"+
                            "		"+
                            "		#outlook a {"+
                            "			padding: 0;"+
                            "		}"+
                            "		/* Force Outlook 2007 and up to provide a \"view in browser\" message. */"+
                            "		"+
                            "		img {"+
                            "			-ms-interpolation-mode: bicubic;"+
                            "			display: block;"+
                            "			outline: none;"+
                            "			text-decoration: none;"+
                            "		}"+
                            "		/* Force IE to smoothly render resized images. */"+
                            "		"+
                            "		body,"+
                            "		table,"+
                            "		td,"+
                            "		p,"+
                            "		a,"+
                            "		li,"+
                            "		blockquote {"+
                            "			-ms-text-size-adjust: 100%;"+
                            "			-webkit-text-size-adjust: 100%;"+
                            "			font-weight: normal!important;"+
                            "		}"+
                            "		/* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */"+
                            "		"+
                            "		.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {"+
                            "			padding-top: 10px !important;"+
                            "		}"+
                            "		/* Force hotmail to push 2-grid sub headers down */"+
                            "		/* ========== Page Styles ========== */"+
                            "		"+
                            "		h1 {"+
                            "			display: block;"+
                            "			font-size: 26px;"+
                            "			font-style: normal;"+
                            "			font-weight: normal;"+
                            "			line-height: 100%;"+
                            "		}"+
                            "		"+
                            "		h2 {"+
                            "			display: block;"+
                            "			font-size: 20px;"+
                            "			font-style: normal;"+
                            "			font-weight: normal;"+
                            "			line-height: 120%;"+
                            "		}"+
                            "		"+
                            "		h3 {"+
                            "			display: block;"+
                            "			font-size: 17px;"+
                            "			font-style: normal;"+
                            "			font-weight: normal;"+
                            "			line-height: 110%;"+
                            "		}"+
                            "		"+
                            "		h4 {"+
                            "			display: block;"+
                            "			font-size: 18px;"+
                            "			font-style: italic;"+
                            "			font-weight: normal;"+
                            "			line-height: 100%;"+
                            "		}"+
                            "		"+
                            "		.flexibleImage {"+
                            "			height: auto;"+
                            "		}"+
                            "		"+
                            "		.linkRemoveBorder {"+
                            "			border-bottom: 0 !important;"+
                            "		}"+
                            "		"+
                            "		table[class=flexibleContainerCellDivider] {"+
                            "			padding-bottom: 0 !important;"+
                            "			padding-top: 0 !important;"+
                            "		}"+
                            "		"+
                            "		body,"+
                            "		#bodyTable {"+
                            "			background-color: #E1E1E1;"+
                            "		}"+
                            "		"+
                            "		#emailHeader {"+
                            "			background-color: #E1E1E1;"+
                            "		}"+
                            "		"+
                            "		#emailBody {"+
                            "			background-color: #FFFFFF;"+
                            "		}"+
                            "		"+
                            "		#emailFooter {"+
                            "			background-color: #E1E1E1;"+
                            "		}"+
                            "		"+
                            "		.nestedContainer {"+
                            "			background-color: #F8F8F8;"+
                            "			border: 1px solid #CCCCCC;"+
                            "		}"+
                            "		"+
                            "		.emailButton {"+
                            "			background-color: #205478;"+
                            "			border-collapse: separate;"+
                            "		}"+
                            "		"+
                            "		.buttonContent {"+
                            "			color: #FFFFFF;"+
                            "			font-family: Helvetica;"+
                            "			font-size: 18px;"+
                            "			font-weight: bold;"+
                            "			line-height: 100%;"+
                            "			padding: 15px;"+
                            "			text-align: center;"+
                            "		}"+
                            "		"+
                            "		.buttonContent a {"+
                            "			color: #FFFFFF;"+
                            "			display: block;"+
                            "			text-decoration: none!important;"+
                            "			border: 0!important;"+
                            "		}"+
                            "		"+
                            "		.emailCalendar {"+
                            "			background-color: #FFFFFF;"+
                            "			border: 1px solid #CCCCCC;"+
                            "		}"+
                            "		"+
                            "		.emailCalendarMonth {"+
                            "			background-color: #205478;"+
                            "			color: #FFFFFF;"+
                            "			font-family: Ubuntu, Helvetica, Arial, sans-serif;"+
                            "			font-size: 16px;"+
                            "			font-weight: bold;"+
                            "			padding-top: 10px;"+
                            "			padding-bottom: 10px;"+
                            "			text-align: center;"+
                            "		}"+
                            "		"+
                            "		.emailCalendarDay {"+
                            "			color: #205478;"+
                            "			font-family: Ubuntu, Helvetica, Arial, sans-serif;"+
                            "			font-size: 60px;"+
                            "			font-weight: bold;"+
                            "			line-height: 100%;"+
                            "			padding-top: 20px;"+
                            "			padding-bottom: 20px;"+
                            "			text-align: center;"+
                            "		}"+
                            "		"+
                            "		.imageContentText {"+
                            "			margin-top: 10px;"+
                            "			line-height: 0;"+
                            "		}"+
                            "		"+
                            "		.imageContentText a {"+
                            "			line-height: 0;"+
                            "		}"+
                            "		"+
                            "		#invisibleIntroduction {"+
                            "			display: none !important;"+
                            "		}"+
                            "		/* Removing the introduction text from the view */"+
                            "		/*FRAMEWORK HACKS & OVERRIDES */"+
                            "		"+
                            "		span[class=ios-color-hack] a {"+
                            "			color: #275100!important;"+
                            "			text-decoration: none!important;"+
                            "		}"+
                            "		/* Remove all link colors in IOS (below are duplicates based on the color preference) */"+
                            "		"+
                            "		span[class=ios-color-hack2] a {"+
                            "			color: #205478!important;"+
                            "			text-decoration: none!important;"+
                            "		}"+
                            "		"+
                            "		span[class=ios-color-hack3] a {"+
                            "			color: #8B8B8B!important;"+
                            "			text-decoration: none!important;"+
                            "		}"+
                            "		/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled."+
                            "			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/."+
                            "			*/"+
                            "		"+
                            "		.a[href^=\"tel\"],"+
                            "		a[href^=\"sms\"] {"+
                            "			text-decoration: none!important;"+
                            "			color: #606060!important;"+
                            "			pointer-events: none!important;"+
                            "			cursor: default!important;"+
                            "		}"+
                            "		"+
                            "		.mobile_link a[href^=\"tel\"],"+
                            "		.mobile_link a[href^=\"sms\"] {"+
                            "			text-decoration: none!important;"+
                            "			color: #606060!important;"+
                            "			pointer-events: auto!important;"+
                            "			cursor: default!important;"+
                            "		}"+
                            "		/* MOBILE STYLES */"+
                            "		"+
                            "		@media only screen and (max-width: 480px) {"+
                            "			/*////// CLIENT-SPECIFIC STYLES //////*/"+
                            "			body {"+
                            "				width: 100% !important;"+
                            "				min-width: 100% !important;"+
                            "			}"+
                            "			/* Force iOS Mail to render the email at full width. */"+
                            "			/* FRAMEWORK STYLES */"+
                            "			/*"+
                            "				CSS selectors are written in attribute"+
                            "				selector format to prevent Yahoo Mail"+
                            "				from rendering media query styles on"+
                            "				desktop."+
                            "				*/"+
                            "			/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/"+
                            "			table[id=\"emailHeader\"],"+
                            "			table[id=\"emailBody\"],"+
                            "			table[id=\"emailFooter\"],"+
                            "			table[class=\"flexibleContainer\"],"+
                            "			td[class=\"flexibleContainerCell\"] {"+
                            "				width: 100% !important;"+
                            "			}"+
                            "			td[class=\"flexibleContainerBox\"],"+
                            "			td[class=\"flexibleContainerBox\"] table {"+
                            "				display: block;"+
                            "				width: 100%;"+
                            "				text-align: left;"+
                            "			}"+
                            "			/*"+
                            "				The following style rule makes any"+
                            "				image classed with 'flexibleImage'"+
                            "				fluid when the query activates."+
                            "				Make sure you add an inline max-width"+
                            "				to those images to prevent them"+
                            "				from blowing out."+
                            "				*/"+
                            "			td[class=\"imageContent\"] img {"+
                            "				height: auto !important;"+
                            "				width: 100% !important;"+
                            "				max-width: 100% !important;"+
                            "			}"+
                            "			img[class=\"flexibleImage\"] {"+
                            "				height: auto !important;"+
                            "				width: 100% !important;"+
                            "				max-width: 100% !important;"+
                            "			}"+
                            "			img[class=\"flexibleImageSmall\"] {"+
                            "				height: auto !important;"+
                            "				width: auto !important;"+
                            "			}"+
                            "			/*"+
                            "				Create top space for every second element in a block"+
                            "				*/"+
                            "			table[class=\"flexibleContainerBoxNext\"] {"+
                            "				padding-top: 10px !important;"+
                            "			}"+
                            "			/*"+
                            "				Make buttons in the email span the"+
                            "				full width of their container, allowing"+
                            "				for left- or right-handed ease of use."+
                            "				*/"+
                            "			table[class=\"emailButton\"] {"+
                            "				width: 100% !important;"+
                            "			}"+
                            "			td[class=\"buttonContent\"] {"+
                            "				padding: 0 !important;"+
                            "			}"+
                            "			td[class=\"buttonContent\"] a {"+
                            "				padding: 15px !important;"+
                            "			}"+
                            "		}"+
                            "		/*  CONDITIONS FOR ANDROID DEVICES ONLY"+
                            "			*   http://developer.android.com/guide/webapps/targeting.html"+
                            "			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;"+
                            "			=====================================================*/"+
                            "		"+
                            "		@media only screen and (-webkit-device-pixel-ratio:.75) {"+
                            "			/* Put CSS for low density (ldpi) Android layouts in here */"+
                            "		}"+
                            "		"+
                            "		@media only screen and (-webkit-device-pixel-ratio:1) {"+
                            "			/* Put CSS for medium density (mdpi) Android layouts in here */"+
                            "		}"+
                            "		"+
                            "		@media only screen and (-webkit-device-pixel-ratio:1.5) {"+
                            "			/* Put CSS for high density (hdpi) Android layouts in here */"+
                            "		}"+
                            "		/* end Android targeting */"+
                            "		/* CONDITIONS FOR IOS DEVICES ONLY"+
                            "			=====================================================*/"+
                            "		"+
                            "		@media only screen and (min-device-width: 320px) and (max-device-width:568px) {}"+
                            "		/* end IOS targeting */"+
                            "	</style>"+
                            "	<!--"+
                            "			Outlook Conditional CSS"+
                            ""+
                            "			These two style blocks target Outlook 2007 & 2010 specifically, forcing"+
                            "			columns into a single vertical stack as on mobile clients. This is"+
                            "			primarily done to avoid the 'page break bug' and is optional."+
                            ""+
                            "			More information here:"+
                            "			http://templates.mailchimp.com/development/css/outlook-conditional-css"+
                            "		-->"+
                            "	<!--[if mso 12]>"+
                            "			<style type=\"text/css\">"+
                            "				.flexibleContainer{display:block !important; width:100% !important;}"+
                            "			</style>"+
                            "		<![endif]-->"+
                            "	<!--[if mso 14]>"+
                            "			<style type=\"text/css\">"+
                            "				.flexibleContainer{display:block !important; width:100% !important;}"+
                            "			</style>"+
                            "		<![endif]-->"+
                            "</head>"+
                            ""+
                            "<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">"+
                            "	<!-- CENTER THE EMAIL // -->"+
                            "	<!--"+
                            "		1.  The center tag should normally put all the"+
                            "			content in the middle of the email page."+
                            "			I added \"table-layout: fixed;\" style to force"+
                            "			yahoomail which by default put the content left."+
                            ""+
                            "		2.  For hotmail and yahoomail, the contents of"+
                            "			the email starts from this center, so we try to"+
                            "			apply necessary styling e.g. background-color."+
                            "		-->"+
                            "	<center style=\"background-color:#E1E1E1;\">"+
                            "		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">"+
                            "			<tr>"+
                            "				<td align=\"center\" valign=\"top\" id=\"bodyCell\">"+
                            "					<!-- EMAIL HEADER // -->"+
                            "					<!--"+
                            "							The table \"emailBody\" is the email's container."+
                            "							Its width can be set to 100% for a color band"+
                            "							that spans the width of the page."+
                            "						-->"+
                            "					<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailHeader\">"+
                            "						<!-- HEADER ROW // -->"+
                            "						<tr>"+
                            "							<td align=\"center\" valign=\"top\">"+
                            "								<!-- CENTERING TABLE // -->"+
                            "								<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "									<tr>"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- FLEXIBLE CONTAINER // -->"+
                            "											<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "												<tr>"+
                            "													<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "														<!-- CONTENT TABLE // -->"+
                            "														<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "															<tr>"+
                            "																<!--"+
                            "																		The \"invisibleIntroduction\" is the text used for short preview"+
                            "																		of the email before the user opens it (50 characters max). Sometimes,"+
                            "																		you do not want to show this message depending on your design but this"+
                            "																		text is highly recommended."+
                            ""+
                            "																		You do not have to worry if it is hidden, the next <td> will automatically"+
                            "																		center and apply to the width 100% and also shrink to 50% if the first <td>"+
                            "																		is visible."+
                            "																	-->"+
                            "																<td align=\"left\" valign=\"middle\" id=\"invisibleIntroduction\" class=\"flexibleContainerBox\" style=\"display:none !important; mso-hide:all;\">"+
                            "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">"+
                            "																		<tr>"+
                            "																			<td align=\"left\" class=\"textContent\">"+
                            "																				<div style=\"font-family:Ubuntu,Helvetica,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\"> Globati - you've been invited to be part of it!</div>"+
                            "																			</td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																</td>"+
                            "																<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">"+
                            "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">"+
                            "																		<tr>"+
                            "																			<td align=\"left\" class=\"textContent\">"+
                            "																				<!-- CONTENT // -->"+
                            "																				<div style=\"font-family:Ubuntu,Helvetica,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\"> If you can't see this message, <a href=\"#\" target=\"_blank\" style=\"text-decoration:none;border-bottom:1px solid #828282;color:#828282;\"><span style=\"color:#828282;\">view it in your browser</span></a>. </div>"+
                            "																			</td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																</td>"+
                            "															</tr>"+
                            "														</table>"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // FLEXIBLE CONTAINER -->"+
                            "										</td>"+
                            "									</tr>"+
                            "								</table>"+
                            "								<!-- // CENTERING TABLE -->"+
                            "							</td>"+
                            "						</tr>"+
                            "						<!-- // END -->"+
                            "					</table>"+
                            "					<!-- // END -->"+
                            "					<!-- EMAIL BODY // -->"+
                            "					<!--"+
                            "							The table \"emailBody\" is the email's container."+
                            "							Its width can be set to 100% for a color band"+
                            "							that spans the width of the page."+
                            "						-->"+
                            "					<table bgcolor=\"#FFFFFF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">"+
                            "						<!-- MODULE ROW // -->"+
                            "						<tr>"+
                            "							<td align=\"center\" valign=\"top\">"+
                            "								<!-- CENTERING TABLE // -->"+
                            "								<table bgcolor=\"#1E90FF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"160px\">"+
                            "									<tr>"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- FLEXIBLE CONTAINER // -->"+
                            "											<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "												<tr>"+
                            "													<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "														<!-- CONTENT TABLE // -->"+
                            "														<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "															<tr>"+
                            "																<td align=\"right\" class=\"textContent\" width=\"41%\"> <img src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/globati.png\" width=\"100\" class=\"flexibleImageSmall\" style=\"max-width:100%;\" alt=\"\" title=\"\" /> </td>"+
                            "																<td align=\"left\" width=\"69%\" style=\"color:#FFFFFF;line-height:125%;font-family:Ubuntu,Helvetica,sans-serif;font-size:50px;font-weight:700;margin-top:0;margin-bottom:3px;text-align:left;\"><b>Globati</b> </td>"+
                            "															</tr>"+
                            "														</table>"+
                            "														<!-- // CONTENT TABLE -->"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // FLEXIBLE CONTAINER -->"+
                            "										</td>"+
                            "									</tr>"+
                            "								</table>"+
                            "								<!-- // CENTERING TABLE -->"+
                            "							</td>"+
                            "						</tr>"+
                            "						<!-- // MODULE ROW -->"+
                            "						<!-- MODULE ROW // -->"+
                            "						<tr>"+
                            "							<td align=\"center\" valign=\"top\">"+
                            "								<!-- CENTERING TABLE // -->"+
                            "								<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "									<tr>"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- FLEXIBLE CONTAINER // -->"+
                            "											<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "												<tr>"+
                            "													<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "														<!-- CONTENT TABLE // -->"+
                            "														<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "															<tr>"+
                            "																<td align=\"left\" valign=\"top\" class=\"flexibleContainerBox\">"+
                            "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90\" style=\"max-width:100%;\">"+
                            "																		<tr>"+
                            "																			<td align=\"left\" class=\"textContent\"> <img src=\""+employee.get_image()+"\" width=\"100\" class=\"flexibleImageSmall\" style=\"max-width:100%;\" alt=\"\" title=\"Text\" /> </td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																</td>"+
                            "																<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">"+
                            "																	<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"350\" style=\"max-width:100%;\">"+
                            "																		<tr>"+
                            "																			<!--<td align=\"left\" class=\"textContent\">"+
                            "																				<h3 style=\"margin-top:0;margin-bottom:3px;padding:10px;\"></h3> </td>-->"+
                            "																			<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">"+
                            "																				<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"350\" style=\"max-width:100%;\">"+
                            "																					<tr>"+
                            "																						<td align=\"left\" class=\"textContent\" style=\"padding-left:30px; padding-right;\">"+
                            "																							<h3 style=\"color:#323333;line-height:125%;font-family:Ubuntu,Helvetica,sans-serif;font-size:29px;font-weight:700;margin-top:0;margin-bottom:3px;text-align:left;\">Hey I'm <a style=\"color:dodgerblue;\" href=\"\" target=\"_blank\">"+employee.get_firstName()+"</a>!</h3>"+
                            "																							<div style=\"text-align:left;font-family:Ubuntu,Helvetica,sans-serif;font-size:15px;margin-bottom:0;color:#323333;line-height:135%;\">"+employee.get_recruitmentMail()+"</div>"+
                            "																						</td>"+
                            "																					</tr>"+
                            "																				</table>"+
                            "																			</td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																	<!-- // CONTENT TABLE -->"+
                            "																</td>"+
                            "															</tr>"+
                            "														</table>"+
                            "														<!-- // FLEXIBLE CONTAINER -->"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // CENTERING TABLE -->"+
                            "										</td>"+
                            "									</tr>"+
                            "									<!-- // MODULE ROW -->"+
                            "									<!-- MODULE ROW // -->"+
                            "									<tr>"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- CENTERING TABLE // -->"+
                            "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "												<tr>"+
                            "													<td align=\"center\" valign=\"top\">"+
                            "														<!-- FLEXIBLE CONTAINER // -->"+
                            "														<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "															<tr>"+
                            "																<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "																	<!-- CONTENT TABLE // -->"+
                            "																	<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "																		<tr>"+
                            "																			<td align=\"left\" valign=\"top\" class=\"flexibleContainerBox\" style=\"background-color:#9C13FF;\">"+
                            "																				<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">"+
                            "																					<tr>"+
                            "																						<td align=\"left\" class=\"textContent\">"+
                            "																							<h3 style=\"color:#FFFFFF;line-height:125%;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:20px;font-weight:700;margin-top:0;margin-bottom:6px;text-align:left;\">What is Globati about?</h3>"+
                            "																							<div style=\"text-align:left;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">Globati is a network that allows your business to team up with people active with tourists to help market your business and get new clientel.</div>"+
                            "																						</td>"+
                            "																					</tr>"+
                            "																				</table>"+
                            "																			</td>"+
                            "																			<td align=\"right\" valign=\"top\" class=\"flexibleContainerBox\" style=\"background-color:gold;\">"+
                            "																				<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">"+
                            "																					<tr>"+
                            "																						<td align=\"left\" class=\"textContent\">"+
                            "																							<h3 style=\"color:#323333;line-height:125%;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:20px;font-weight:700;margin-top:0;margin-bottom:6px;text-align:left;\">Why advertise on Globati?</h3>"+
                            "																							<div style=\"text-align:left;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#323333;line-height:135%;\">Globati is unique advertising opportunity because it is not just an add on a website, but a partnership with people who have constant contact with tourists.</div>"+
                            "																						</td>"+
                            "																					</tr>"+
                            "																				</table>"+
                            "																			</td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																	<!-- // CONTENT TABLE -->"+
                            "																</td>"+
                            "															</tr>"+
                            "														</table>"+
                            "														<!-- // FLEXIBLE CONTAINER -->"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // CENTERING TABLE -->"+
                            "										</td>"+
                            "									</tr>"+
                            "									<!-- // MODULE ROW -->"+
                            "									<!-- MODULE ROW // -->"+
                            "									<tr>"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- CENTERING TABLE // -->"+
                            "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "												<tr>"+
                            "													<td align=\"center\" valign=\"top\">"+
                            "														<!-- FLEXIBLE CONTAINER // -->"+
                            "														<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "															<tr>"+
                            "																<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "																	<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">"+
                            "																		<tr>"+
                            "																			<td align=\"center\" valign=\"top\">"+
                            "																				<!-- CONTENT TABLE // -->"+
                            "																				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "																					<tr>"+
                            "																						<td valign=\"top\" class=\"textContent\">"+
                            "																							<h3 style=\"color:#32333;line-height:125%;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:20px;font-weight:700;margin-top:0;margin-bottom:6px;text-align:left;\">Signing up is easy!</h3>"+
                            "																							<div style=\"text-align:left;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;margin-top:3px;color:#323333;line-height:135%;\">Click the button below to create a globati partnership that will bring new customers to your business.</div>"+
                            "																						</td>"+
                            "																					</tr>"+
                            "																				</table>"+
                            "																				<!-- // CONTENT TABLE -->"+
                            "																			</td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																</td>"+
                            "															</tr>"+
                            "														</table>"+
                            "														<!-- // FLEXIBLE CONTAINER -->"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // CENTERING TABLE -->"+
                            "										</td>"+
                            "									</tr>"+
                            "									<!-- // MODULE ROW -->"+
                            "									<!-- MODULE ROW // -->"+
                            "									<tr>"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- CENTERING TABLE // -->"+
                            "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "												<tr style=\"padding-top:0;\">"+
                            "													<td align=\"center\" valign=\"top\">"+
                            "														<!-- FLEXIBLE CONTAINER // -->"+
                            "														<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "															<tr>"+
                            "																<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "																	<!-- CONTENT TABLE // -->"+
                            "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color:deeppink;\">"+
                            "																		<tr>"+
                            "																			<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\"> <a style=\"color:#FFFFFF;text-decoration:none;font-family:Ubuntu,Helvetica,sans-serif;font-size:18px;line-height:135%;\" href=\""+Paths.getActiveCreateAddLink()+employee.get_id()+"\" target=\"_blank\"><b>LET'S START!</a> </td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																	<!-- // CONTENT TABLE -->"+
                            "																</td>"+
                            "															</tr>"+
                            "														</table>"+
                            "														<!-- // FLEXIBLE CONTAINER -->"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // CENTERING TABLE -->"+
                            "										</td>"+
                            "									</tr>"+
                            "									<!-- // MODULE ROW -->"+
                            "									<!-- MODULE ROW // -->"+
                            "									<tr bgcolor=\"#323333\">"+
                            "										<td align=\"center\" valign=\"top\">"+
                            "											<!-- CENTERING TABLE // -->"+
                            "											<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "												<tr>"+
                            "													<td align=\"center\" valign=\"top\">"+
                            "														<!-- FLEXIBLE CONTAINER // -->"+
                            "														<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">"+
                            "															<tr>"+
                            "																<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">"+
                            "																	<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">"+
                            "																		<tr>"+
                            "																			<td align=\"center\" valign=\"top\">"+
                            "																				<!-- CONTENT TABLE // -->"+
                            "																				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                            "																					<tr>"+
                            "																						<td valign=\"top\" class=\"textContent\">"+
                            "																							<div style=\"text-align:center;font-family:Ubuntu,Helvetica,sans-serif;font-size:15px;margin-bottom:0;margin-top:3px;color:#FFFFFF;line-height:135%;\"> © 2017 <a href=\"https://globati.com/\" target=\"_blank\" style=\"text-decoration:none;color:#FFFFFF;\"><span style=\"color:#FFFFF;\">Globati</span></a>. All rights reserved.</div>"+
                            "																						</td>"+
                            "																					</tr>"+
                            "																					<tr>"+
                            "																						<td valign=\"top\" class=\"textContent\">"+
                            "																							<div style=\"text-align:center;font-family:Ubuntu,Helvetica,sans-serif;font-size:15px;margin-bottom:0;margin-top:3px;color:#FFFFFF;line-height:135%;\"> Contact "+employee.get_firstName()+" at "+employee.get_email()+" if you have any questions about creating a partnership.<span style=\"color:#FFFFF;\">"+
                            "																						</td>"+
                            "																					</tr>"+

                            "																				</table>"+
                            "																				<!-- // CONTENT TABLE -->"+
                            "																			</td>"+
                            "																		</tr>"+
                            "																	</table>"+
                            "																</td>"+
                            "															</tr>"+
                            "														</table>"+
                            "														<!-- // FLEXIBLE CONTAINER -->"+
                            "													</td>"+
                            "												</tr>"+
                            "											</table>"+
                            "											<!-- // CENTERING TABLE -->"+
                            "										</td>"+
                            "									</tr>"+
                            "									<!-- // MODULE ROW -->"+
                            "									<!-- // END -->"+
                            "							</td>"+
                            "						</tr>"+
                            "						</table>"+
                            "				</td>"+
                            "			</tr>"+
                            "			</table>"+
                            "	</center>"+
                            "</body>"+
                            ""+
                            "</html>"

            );

        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }

    }

    public static boolean sendForgottenPasswordEmail(String email, String globatiuser, String apitoken) throws Exception {

        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Here is your Globati username, click on the link to reset your password.");
        Content textBody = new Content().withData(
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "    <meta charset=\"UTF-8\">" +
                        "" +
                        "</head>" +
                        "<body>" +
                        "    <h2>Your globati username is: " + globatiuser + "</h2>" +
                        "    <h3><a href=\"" + Paths.getActiveStaticMembers() + "changeyourpassword/" + apitoken + "\">Click here to change your password</a></h3>" +
                        "</body>" +
                        "</html>"
        );

        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }

    }
}