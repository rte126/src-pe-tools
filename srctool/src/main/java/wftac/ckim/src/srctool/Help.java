package wftac.ckim.src.srctool;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.springframework.context.ApplicationContext;

public class Help implements CommandRunner {

	private String version;
	
	public void perform(Options options,CommandLine cmd) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "src-tool ver:" + version, options );

	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
