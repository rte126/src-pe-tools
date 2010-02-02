package wftac.ckim.src.srctool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
	
	Main(){
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("wftac/ckim/src/srctool/srctool.xml");

		Main main = new Main();
		Options options = main.setOptions();
		CommandLineParser parser = new GnuParser();
	
		try {
			CommandLine cmd = parser.parse( options, args);
			
			CommandRunner cr;
			if( cmd.hasOption( "gc" ) ) {
				cr = (CommandRunner)ctx.getBean("gc");
				cr.perform(options,cmd);
				System.exit(0);
			}
			if( cmd.hasOption( "devicegrouplist" ) ) {
				cr = (CommandRunner)ctx.getBean("devicegrouplist");
				cr.perform(options,cmd);
				System.exit(0);
			}
			if( cmd.hasOption( "deviceremove" ) ) {
				cr = (CommandRunner)ctx.getBean("deviceremove");
				cr.perform(options,cmd);
				System.exit(0);
			}
			if( cmd.hasOption( "devicegroupremove" ) ) {
				cr = (CommandRunner)ctx.getBean("devicegroupremove");
				cr.perform(options,cmd);
				System.exit(0);
			}
			cr = (CommandRunner)ctx.getBean("help");
			if( cmd.hasOption("help")){
				cr.perform(options,cmd);
				System.exit(0);
			}
			cr.perform(options,cmd);

		} catch( ParseException e ){
			System.err.println( "Parsing failed.  Reason: " + e.getMessage() );
		}
		
	}

	private Options setOptions() {
		Options options = new Options();
		options.addOption( "help", false, "print this message" );
		options.addOption( "gc", true, "issue full GC on <SAE>" );
		options.addOption( "devicegrouplist", false, "list device groups" );
		options.addOption( "deviceremove", true, "remove device" );
		options.addOption( "devicegroupremove", true, "remove device group" );
		
		return options;
	}

}
