package org.myrobotlab.service.meta;

import org.myrobotlab.framework.Platform;
import org.myrobotlab.logging.LoggerFactory;
import org.myrobotlab.service.meta.abstracts.MetaData;
import org.slf4j.Logger;

public class ChessGameMeta extends MetaData {
	private static final long serialVersionUID = 1L;
	public final static Logger log = LoggerFactory.getLogger(ChessGameMeta.class);

	/**
	 * This static method returns all the details of the class without it having to
	 * be constructed. It has description, categories, dependencies, and peer
	 * definitions.
	 * 
	 * @return MetaData - returns all the data
	 * 
	 */
	public ChessGameMeta() {

		Platform platform = Platform.getLocalInstance();
		addCategory("game");
		addDependency("ChessBoard", "ChessBoard", "1.0.0");
		addDescription("Would you like to play a game?");

	}
}
