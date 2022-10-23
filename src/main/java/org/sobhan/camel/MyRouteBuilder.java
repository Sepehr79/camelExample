package org.sobhan.camel;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=1000")
                .transform(simple("Before Seda"))
                .to("seda:c1")
                .to("seda:c2")
            .log("${body}");

        from("seda:c1")
                .transform(simple("On c1 seda"));

        from("seda:c2")
                .transform(simple("On c2 seda"));
    }
}
