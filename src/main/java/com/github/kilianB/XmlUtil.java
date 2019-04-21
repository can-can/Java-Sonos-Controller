package com.github.kilianB;

import com.github.kilianB.sonos.model.PlayList;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {

  public static List<PlayList> parsePlayLists(String playListsXml) throws IOException {
    List<PlayList> result = new ArrayList<>();
    playListsXml = StringUtils.replace(playListsXml, "?", "%3F");
    playListsXml = StringUtils.replace(playListsXml, "&", "%26");
    ByteArrayInputStream is = new ByteArrayInputStream(playListsXml.getBytes());
    SAXReader reader = new SAXReader();
    try {
      Document document = reader.read(is);
      Node node = document.selectSingleNode("//Result");
      List<Element> containerEles =
          ((DefaultElement) node).element("DIDL-Lite").elements("container");
      for (Element containerEle : containerEles) {
        Element title = containerEle.element("title");
        Element res = containerEle.element("res");
        result.add(new PlayList(title.getText(), res.getText()));
      }
    } catch (DocumentException e) {
      throw new IOException(e);
    }
    return result;
  }
}
