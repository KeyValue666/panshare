package com.panshare.client.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Key&Value
 * @Date 2024/5/8 15:05
 * @Version 1.0
 */
@Component
@Slf4j
public class SensitiveWordFilter {
    private static final String REPLACEMENT = "***";
    private TrieNode root = new TrieNode();
    @PostConstruct
    public void init() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/sensitive-word.txt");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = bufferedReader.readLine()) != null) {
                this.addKeyWord(keyword);
            }
        } catch (Exception e) {
            log.error("加载敏感词文件失败:{}", e.getMessage());
        }
    }
    private void addKeyWord(String keyword) {
        TrieNode tempNode = root;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }
            tempNode = subNode;
            if (i == keyword.length() - 1) {
                tempNode.setKeyWordEnd(true);
            }
        }
    }
    public String filterSensitiveWords(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        TrieNode tempNode = root;
        int begin = 0;
        int position = 0;
        StringBuilder sb = new StringBuilder();
        while (position < text.length()) {
            char c = text.charAt(position);
            if (isSymbol(c)) {
                if (tempNode == root) {
                    sb.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                sb.append(text.charAt(begin));
                position = ++begin;
                tempNode = root;
            } else if (tempNode.isKeyWordEnd()) {
                sb.append(REPLACEMENT);
                begin = ++position;
                tempNode = root;
            } else {
                position++;
            }
        }
        sb.append(text.substring(begin));
        return sb.toString();
    }
    private boolean isSymbol(Character c) {
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
    private class TrieNode {
        private boolean isKeyWordEnd = false;
        private Map<Character, TrieNode> subNodes = new HashMap<>();
        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }
        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }
        public Map<Character, TrieNode> getSubNodes() {
            return subNodes;
        }
        public void setSubNodes(Map<Character, TrieNode> subNodes) {
            this.subNodes = subNodes;
        }
        public void addSubNode(Character character, TrieNode trieNode) {
            subNodes.put(character, trieNode);
        }
        public TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }
    }
}