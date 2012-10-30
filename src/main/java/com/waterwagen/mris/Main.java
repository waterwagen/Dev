package com.waterwagen.mris;

import static com.waterwagen.mris.Utils.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class Main 
{
	public static final String MAIN_WINDOW_COMPONENT_NAME = "testWindow";

	public static void main(String[] argv) throws InvocationTargetException, InterruptedException
	{
		boolean visible = argv == null ||
							argv.length != 1 ||
							!argv[0].startsWith("mris.headless=") ||
							!argv[0].endsWith("true");
		new Main().start(visible);
	}
	
	private void start(boolean visible) throws InvocationTargetException, InterruptedException
	{
		Injector injector = Guice.createInjector(new AbstractModule()
		{
			@Override
			protected void configure() 
			{
				bind(MrisMainWindow.class).in(Singleton.class); // not strictly necessary, only here to signal Guice to eagerly prepare dependencies
				bind(TransformRequestListener.class).to(ContactsTransformer.class).in(Singleton.class);
				bind(ContactsParser.class).to(SimpleContactsParser.class).in(Singleton.class);
				bind(EmailAddressFinder.class).to(LocalLookupEmailFinder.class).in(Singleton.class);
				bind(new TypeLiteral<Map<String,String>>(){}).annotatedWith(Names.named("emailAddressMapping")).toInstance(buildMap(ID_FOR_JAKE + ":" + EMAILADDRESS_FOR_JAKE));
				bind(ContactsTransformedListener.class).to(SimpleFileWriter.class).in(Singleton.class);
				bind(String.class).annotatedWith(Names.named("mrisOutputFilePath")).toInstance(SIMPLE_OUTPUT_FILE_PATH);
				
			}
		});
				
		startGui(visible, injector);
	}
	
	private void startGui(final boolean visible, final Injector injector) throws InvocationTargetException, InterruptedException
	{
		SwingUtilities.invokeAndWait(new Runnable()
		{
			@Override
			public void run()
			{
				MrisMainWindow main_window = injector.getInstance(MrisMainWindow.class);
				main_window.setName(MAIN_WINDOW_COMPONENT_NAME);
				main_window.display();
//				main_window.setVisible(visible);
			}
		});
	}
}