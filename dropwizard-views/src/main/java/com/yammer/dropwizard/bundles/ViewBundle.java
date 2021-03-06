package com.yammer.dropwizard.bundles;

import com.yammer.dropwizard.Bundle;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.templates.ViewMessageBodyWriter;

/**
 * A {@link Bundle} which enables the rendering of FreeMarker views by your service.
 *
 * <p>A view is the combination of a Freemarker file ({@code .ftl}) and a model, which is any
 * Java object:</p>
 *
 * <pre><code>
 * public class PersonView extends View&lt;Person&gt; {
 *     public PersonView(Person person) {
 *         super("profile.ftl", person);
 *     }
 * }
 * </code></pre>
 *
 *<p>The {@code "profile.ftl"} is the path of the template relative to the class name. If this
 * class was {@code com.example.service.PersonView}, Freemarker would then look for the file
 * {@code src/main/resources/com/example/service/PersonView/profile.ftl}. If the template path
 * starts with a slash (e.g., {@code "/hello.ftl"}), Freemarker will look for the file {@code
 * src/main/resources/hello.ftl}.
 *
 * <p>A resource method with a view would looks something like this:</p>
 *
 * <pre><code>
 * \@GET
 * public PersonView getPerson(\@PathParam("id") String id) {
 *     return new PersonView(dao.find(id));
 * }
 * </code></pre>
 *
 * <p>Freemarker templates look something like this:</p>
 *
 * <pre>{@code
 * <#-- @ftlvariable name="" type="com.example.service.Person" -->
 * <html>
 *     <body>
 *         <h1>Hello, ${name?html}!</h1>
 *     </body>
 * </html>
 * }</pre>
 *
 * <p>In this template, {@code ${name}} calls {@code Person#getName()}, and the {@code ?html}
 * escapes all HTML control characters in the result. The {@code ftlvariable} comment at the top
 * indicate to Freemarker (and your IDE) that the root object is a {@code Person}, allowing for
 * better typesafety in your templates.</p>
 *
 * @see <a href="http://freemarker.sourceforge.net/docs/index.html">FreeMarker Manual</a>
 */
public class ViewBundle implements Bundle {
    @Override
    public void initialize(Environment environment) {
        environment.addProvider(ViewMessageBodyWriter.class);
    }
}
