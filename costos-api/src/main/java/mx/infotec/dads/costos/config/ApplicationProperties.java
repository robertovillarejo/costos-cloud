package mx.infotec.dads.costos.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Costosapi.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private List<String> allowedDataFrameMediaTypes;

    public List<String> getAllowedDataFrameMediaTypes() {
        return allowedDataFrameMediaTypes;
    }

    public void setAllowedDataFrameMediaTypes(List<String> allowedDataFrameMediaTypes) {
        this.allowedDataFrameMediaTypes = allowedDataFrameMediaTypes;
    }

}
