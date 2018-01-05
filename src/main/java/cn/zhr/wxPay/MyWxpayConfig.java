package cn.zhr.wxPay;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyWxpayConfig implements WXPayConfig {

	@Override
	public String getAppID() {
		return "wx411bd4a186a50463";
//		return "wxda66bc48c2d9d956";
	}

	@Override
	public String getMchID() {
		return"1446946202";
//		return"1233478402";
	}

	@Override
	public String getKey() {
		return "39545b738e9f2bd18743022cab7591b5";
//		return "cmbc805zhudanzhongwenfangcuitaow";
	}

	@Override
	public InputStream getCertStream() {
        byte[] certData = null;
		try {
			String certPath = "C://Users//Administrator//Desktop//cert//apiclient_cert.p12";
			File file = new File(certPath);
			InputStream certStream = new FileInputStream(file);
			certData = new byte[(int) file.length()];
			certStream.read(certData);
			certStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(certData);
        return certBis;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return 2000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return 10000;
	}

}
