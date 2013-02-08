package org.hanuna.gitalk.common.compressedlist;

import org.hanuna.gitalk.common.compressedlist.generator.Generator;

import static junit.framework.Assert.assertEquals;

/**
 * @author erokhins
 */
public class RunCompressedListTest<T> {
    private final CompressedList<T> compressedList;
    private final Generator<T> generator;

    public RunCompressedListTest(CompressedList<T> compressedList, Generator<T> generator) {
        this.compressedList = compressedList;
        this.generator = generator;
    }

    private String CompressedListStr() {
            StringBuilder s = new StringBuilder();
        for (int i = 0; i < compressedList.getList().size(); i++) {
            T listElement = compressedList.getList().get(i);
            s.append(listElement).append(" ");
        }
        return s.toString();
    }

    private String ActualListStr() {
        StringBuilder s = new StringBuilder();
        T t = generator.generateFirst();
        s.append(t).append(" ");
        for (int i = 1; i < compressedList.getList().size(); i++) {
            t = generator.generate(t, 1);
            s.append(t).append(" ");
        }
        return s.toString();
    }

    public void assertList() {
        assertList("");
    }
    public void assertList(String message) {
        assertEquals(message, ActualListStr(), CompressedListStr());
    }

    public void runReplace(Replace replace) {
        compressedList.recalculate(replace);
        assertList(replaceToStr(replace));
    }


    public String replaceToStr(Replace replace) {
        return "Replace: from: " + replace.from() + ", to: " + replace.to() + ", addedElementCounts: " + replace.addedElementCount();
    }

}
