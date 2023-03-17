import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class SettingsFileXML {
    protected boolean enabled;
    protected File fileName;
    protected String format;

    public SettingsFileXML() {
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public File getFileName() {
        return fileName;
    }

    public String getFormat() {
        return format;
    }

    public void readXML(Node node, SettingsFileXML load, SettingsFileXML save, SettingsFileXML log) {
        String currentNodeName = "";
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                //System.out.println("Текущий узел: " + currentNode.getNodeName());
                currentNodeName = currentNode.getNodeName();
                if (currentNodeName == "load") {
                    setReadSettings(load, currentNode);
                }
                ;
                if (currentNodeName == "save") {
                    setReadSettings(save, currentNode);
                }
                ;
                if (currentNodeName == "log") {
                    setReadSettings(log, currentNode);
                }
                ;
            }
        }
        //Проверка что считали корректно
        System.out.println(load.toString());
        System.out.println(save.toString());
        System.out.println(log.toString());

    }

    public void setReadSettings(SettingsFileXML setting, Node currentNode) {
        String nodeName = "";
        String nodeValue = "";
        NodeList nodeProps = currentNode.getChildNodes();
        for (int j = 0; j < nodeProps.getLength(); j++) {
            Node nodeProp = nodeProps.item(j);
            // Если нода не текст, то это один из параметров
            if (nodeProp.getNodeType() != Node.TEXT_NODE) {
                nodeName = nodeProp.getNodeName();
                nodeValue = nodeProp.getChildNodes().item(0).getTextContent();

                if (nodeName == "enabled") {
                    setting.setEnabled(Boolean.valueOf(nodeValue));
                }
                if (nodeName == "fileName") {
                    File file = new File(nodeValue);
                    setting.setFileName(file);
                }
                if (nodeName == "format") {
                    setting.setFormat(nodeValue);
                }
            }
        }
    }

    @Override
    public String toString() {
        return enabled + " " + fileName + " " + format;
    }

}
