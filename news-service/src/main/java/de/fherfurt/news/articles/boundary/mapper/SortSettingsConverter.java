package de.fherfurt.news.articles.boundary.mapper;

import de.fherfurt.news.articles.business.modules.entity.SortSettings;
import de.fherfurt.news.client.options.PreviewRequestClient;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

/**
 * this class is used to convert the {@link PreviewRequestClient.SortSettingsClient} into {@link SortSettings}
 *
 * @author Dennis Rinck <dennis.rinck@fh-erfurt.de>
 */
public class SortSettingsConverter extends AbstractConverter<PreviewRequestClient.SortSettingsClient, SortSettings> {
    @Override
    protected SortSettings convert(PreviewRequestClient.SortSettingsClient sortSettingsClient) {
        TypeMap<PreviewRequestClient.SortSettingsClient, SortSettings> mapper = new ModelMapper().createTypeMap(PreviewRequestClient.SortSettingsClient.class, SortSettings.class);
        return mapper.map(sortSettingsClient);
    }
}
