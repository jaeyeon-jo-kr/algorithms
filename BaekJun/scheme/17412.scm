(require-extension srfi-1)

(define (have-next? point edge)
        (or (= point (first edge))
            (= point (last edge))))
(have-next? 1 '(1 2))

(define (get-next-point current edge)
    (cond 
        ((= current (first edge)) (last edge))
        ((= current (last edge)) (first edge))
        (else #t)))

(get-next-point 1 '(1 2))

(define (next-points current-point edges)
    (let* ((filtered (filter (lambda (edge) (have-next? current-point edge)) edges))
           (points (map (lambda (edge) (get-next-point current-point edge)) filtered)))
           points))

(next-points 2 '((3 2) (2 4) (4 6)))

(define (find-step paths edges)
    (map (lambda (path) 
            (cons (next-points (last path) edges) path)) paths))

(find-step '((1)) '((1 3) (3 5) (5 6) (6 2)))

(define (flat-by-first path)
    (map (lambda (point) (cons point (cdr path))) (first path)))

(flat-by-first '((2 5) 6 4 1))

(define (flat-paths paths)
    (reduce append '() paths))

(flat-paths '(((1 2) (1 3)) ((1 2) (4 5))))

(define (contain-count point path)
    (length (filter (lambda (p) (= p point)) path)))

(define (duplicate-list? path)
    (any (lambda (p) 
            (< 1 (contain-count p path))) path))

(duplicate-list? '(1 2 3 4 5 6))

(define (intersect? path1 path2)
    (if (or (< (length path1) 2)
            (< (length path2) 2))
        #f
        (let ((a1 (first path1))
          (a2 (second path1))
          (b1 (first path2))
          (b2 (second path2)))
          (if (and (= a1 b1) (= a2 b2))
            #t
            (or (intersect? (cdr path1) path2)
                (intersect? path1 (cdr path2)))))))

(intersect? '(1 2 3 4 5 6) '(3 4 5 7))

(define (contains-intersect? path paths)
    (let ((filtered (filter 
        (lambda (p) (intersect? path p)) 
        paths)))
        (< 0 (length filtered))))

(contains-intersect? '(1 2 3) '((1 2 3) (4 5 6) (7 8 9)))
(contains-intersect? '(1 2 3) '((8 5 7) (4 5 6) (7 8 9)))

(define (exclude-intersection included-paths remains)
    (if (zero? (length remains))
        included-paths
        (let ((path (first remains)))
            (if (contains-intersect? path included-paths)
                (exclude-intersection included-paths (cdr remains))
                (exclude-intersection (cons path included-paths) (cdr remains))))))

(exclude-intersection '() '((2 3 4 1) (2 4 5 1)))
(exclude-intersection '() '((2 3 4 1) (2 3 5 1)))

(define (explore-step paths edges)
    (let* ((paths (find-step paths edges))
            (paths (map flat-by-first paths))
            (paths (flat-paths paths))
            (paths (exclude-intersection (list) paths)))
            paths))
(explore-step '((1)) '((1 3) (1 4) (1 5)))

(define (all-found? paths)
    (every (lambda (path)
            (and (= 1 (last path))
                (= 2 (first path)))) paths))

(all-found? '((1 3) (1 4) (1 5)))
(all-found? '((2 3 1) (2 4 1) (2 5 1)))

(define (explore paths edges)
    (if (all-found? paths)
        paths
        (explore (explore-step paths edges) edges)))


(define (initialize edges)
    (map (lambda (edge)
        (cons 0 (cons 1 edge))) edges))

(define (add-w edge)
    (let* ((current (car edge))
            (max (cadr edge)))
        (if (< current max)
            (cons (+ current 1) (cdr edge))
            edge)))

(add-w '(0 1 2 3))

(define (available-edges start edges)
    (filter 
            (lambda (edge) 
                (and (zero? (car edge)) (= start (caddr edge)))) edges))

(define (visit-until-last visited edges)
        (let* ((last-point (last (first visited))))
            (if (= last-point 2) visited
                (let* ((next-edges (available-edges last-point edges))
                    (results (map (lambda (next) 
                                    (visit-until-last (cons next visited) edges)) 
                                next-edges))
                    (filtered (filter (lambda (n) n) results)))
                    (if (null? filtered) 
                        #f
                        (first filtered))))))

(define (contains? edge edges)
    (any (lambda (e) 
            (and (= (cadddr edge) (cadddr e))
                (= (caddr edge) (caddr e)))) edges))

(define (update-edges path edges)
    (map (lambda (edge) 
            (if (contains? edge path)
                (add-w edge)
                edge))
    edges))

(define (solve edges)
    (let* ((edges (initialize edges))
            (first-edges (available-edges 1 edges)))
        (fold (lambda (first-edge edges)
                (let* ((path (visit-until-last (list first-edge) edges)))
                    (if path
                        (update-edges path edges)
                        edges))) edges first-edges)))

(define (get-answer edges)
    (length
        (filter (lambda (edge) 
                (and (= (last edge) 2)
                    (= (first edge) 1))) edges)))

(define n (read))

(define p (read))

(define (read-edges cnt edges)
        (if (< cnt p)
            (read-edges (+ cnt 1) (cons (list (read) (read)) edges))
            edges))

(define edges (read-edges 0 '()))

(display (get-answer (solve edges)))

