package wftac.ckim.xml.loaders;

import java.awt.Toolkit;
import java.awt.Image;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ResourceLoader {

	static public Properties loadProperties(String name) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();

		if (loader != null) {
			URL url = loader.getResource(name);
			if (url == null) {
				url = loader.getResource("/" + name);
			}
			if (url != null) {
				try {
					InputStream in = url.openStream();
					Properties props = new Properties();
					props.load(in);
					return props;
				} catch (IOException ioe) {
				}
			}
		}

		return null;
	}

	static public Image loadImage(String name) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();

		if (loader != null) {
			URL url = loader.getResource(name);
			if (url == null) {
				url = loader.getResource("/" + name);
			}
			if (url != null) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img = tk.getImage(url);
				return img;
			}
		}

		return null;
	}

}
