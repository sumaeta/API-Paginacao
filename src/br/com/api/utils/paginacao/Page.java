package br.com.api.utils.paginacao;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Page<T> {

    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;

    private Page(Builder<T> builder) {
        this.content = builder.content;
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.totalElements = builder.totalElements;
    }

    public static class Builder<T> {
        private List<T> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        /**
         * Definir o número da página solicitada
         */
        public Builder<T> pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        /**
         *  Define o tamanho da página
         */
        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }
        
        /**
         * @implNote A ordenação por padrão é ASC e não aceita repetição.
         * 
         * Não é possível ordenar por data.
         * 
         * @return Builder generico ordenado de forma descrecente
         */
        public Builder<T> descendente(){
        	NavigableSet<T> lista = new TreeSet<>(content);
        	List<T> retorno = new ArrayList<>(lista.descendingSet());
        	this.content = retorno;
        	return this;
        }
        
        /**
         * 
         * @param content Recebe por parâmetro a lista que irá ser fragmentada para gerar a paginação.
         * @return Builder generico contendo apenas uma lista com a quantidade de elementos passado por parâmetro.
         */
		public Builder<T> content(List<T> content) {
			
			int totalElements = content.size();
			int startIndex = pageNumber * pageSize;
			int endIndex = Math.min(startIndex + pageSize, totalElements);

			this.content = new ArrayList<>(content.subList(startIndex, endIndex));
			return this;
		}
		
		/**
		 * 
		 * @param totalElements
		 * @return total de elementos da lista, não na representação paginada
		 */
        public Builder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Page<T> build() {
            return new Page<>(this);
        }
    }

	public List<T> getContent() {
		return content;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}
	
	public int getTotalPages() {
        return (int) Math.ceil((double) totalElements / pageSize);
    }
	
	public boolean hasNext() {
        return pageNumber < getTotalPages();
    }

    public boolean hasPrevious() {
        return pageNumber > 0;
    }
	
	public boolean isFirst() {
        return !hasPrevious();
    }

    public boolean isLast() {
        return !hasNext();
    }

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("{\n [Page \n   [");
		if (content != null) {
			builder2.append("content: ");
			builder2.append(content);
			builder2.append(", \n ");
		}
		builder2.append("pageNumber: ");
		builder2.append(pageNumber);
		builder2.append(", \n pageSize: ");
		builder2.append(pageSize);
		builder2.append(", \n totalElements: ");
		builder2.append(totalElements);
		builder2.append(", \n totalPages: ");
		builder2.append(getTotalPages());
		builder2.append("]\n}");
		return builder2.toString();
	}
    
}

