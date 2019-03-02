package ru.mborisov.parser;

import ru.mborisov.entity.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class InstagramCommentsParser {
    public List<Comment> parse(String html) {
        List<Comment> result = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements commentSections = document.select("main ul li[role=\"menuitem\"]");
        for (Element commentSection : commentSections) {
            Element authorTag = commentSection.selectFirst("a[title]");
            Element commentTag = commentSection.selectFirst("span");
            Comment comment = new Comment(
                    authorTag.attr("title"),
                    commentTag.text()
            );
            result.add(comment);
        }
        return result;
    }
}
