package collectionmanager;

import java.util.concurrent.TimeUnit;

import org.springframework.web.client.RestTemplate;

import collectionmanager.api.games.GameResource;

import info.novatec.testit.webtester.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.pagefragments.GenericElement;
import info.novatec.testit.webtester.pagefragments.PageFragment;
import info.novatec.testit.webtester.pagefragments.Table;
import info.novatec.testit.webtester.waiting.Wait;


public class PsnProfilesGamesImporter {

    public static void main(String... args) {
        Browser browser = new FirefoxFactory().createBrowser();
        try {
            doImport(browser);
        } finally {
            browser.close();
        }
    }

    private static void doImport(Browser browser) {
        browser.open("https://psnprofiles.com/Avaj85");

        for (int i = 0; i < 5; i++) {
            GenericElement footer = browser.find(".footer");
            browser.currentWindow().scrollTo(footer);
            Wait.exactly(2, TimeUnit.SECONDS);
        }

        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/api/games";

        browser.find(Table.class).by("#gamesTable").allRows().forEach(tableRow -> {
            String name = tableRow.find(".title").getVisibleText();
            int progress = Integer.parseInt(tableRow.find(".progress-bar > span").getVisibleText().replace("%", ""));
            boolean done = progress == 100;
            tableRow.find(".platforms").findMany("span").map(PageFragment::getVisibleText).forEach(platform -> {
                GameResource game = new GameResource();
                game.setName(name);
                game.setPlatform(platform);
                game.setProgress(progress);
                game.setDone(done);
                template.postForLocation(url, game);
                System.out.println("created: " + game);
            });
        });

    }

}
