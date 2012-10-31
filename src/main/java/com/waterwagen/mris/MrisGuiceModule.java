package com.waterwagen.mris;

import static com.waterwagen.mris.Utils.EMAILADDRESS_FOR_JAKE;
import static com.waterwagen.mris.Utils.ID_FOR_JAKE;
import static com.waterwagen.mris.Utils.SIMPLE_OUTPUT_FILE_PATH;
import static com.waterwagen.mris.Utils.buildMap;

import java.util.Map;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public final class MrisGuiceModule extends AbstractModule
{
	@Override
	protected void configure() 
	{
		bind(MrisMainWindow.class).in(Singleton.class); // not strictly necessary, only here to signal Guice to eagerly prepare dependencies
		bind(TransformRequestListener.class).to(ContactsTransformer.class).in(Singleton.class);
		bind(ContactsParser.class).to(AgentListsExportContactsParser.class).in(Singleton.class);
		bind(EmailAddressFinder.class).to(LocalLookupEmailFinder.class).in(Singleton.class);
		bind(new TypeLiteral<Map<String,String>>(){}).annotatedWith(Names.named("emailAddressMapping")).toInstance(buildMap(ID_FOR_JAKE + ":" + EMAILADDRESS_FOR_JAKE));
		bind(ContactsTransformedListener.class).to(SimpleFileWriter.class).in(Singleton.class);
		bind(String.class).annotatedWith(Names.named("mrisOutputFilePath")).toInstance(SIMPLE_OUTPUT_FILE_PATH);
		
	}
}