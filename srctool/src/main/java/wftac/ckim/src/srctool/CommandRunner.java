package wftac.ckim.src.srctool;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.springframework.context.ApplicationContext;

public interface CommandRunner {
	public void perform(Options options, CommandLine cmd);
}
