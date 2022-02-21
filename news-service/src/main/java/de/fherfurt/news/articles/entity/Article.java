package de.fherfurt.news.articles.entity;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;
/**
 * TODO implement Data Objetcs
 * {@link NewsPerson}
 * {@link NewsAppointment}
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 * */
public @Data class Article extends ArticlePreview{
    private String content;
    private Set<NewsPerson> responsiblePersons;
    private List<BufferedImage> imageList;
    private NewsPerson author;
    private NewsAppointment appointment;

}
